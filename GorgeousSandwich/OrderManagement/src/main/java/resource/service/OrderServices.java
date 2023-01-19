package resource.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import resource.dto.OrderDto;
import resource.dto.OrderItemDto;
import resource.dto.responseDto.OrderResponseDto;
import resource.mapper.IOrderMapper;
import resource.model.Order;
import resource.model.OrderItem;
import resource.model.external.PROMOTIONKIND;
import resource.model.external.Promotion;
import resource.model.external.Sandwich;
import resource.repository.IOrderRepository;
import resource.service.interfaces.IOrderService;
import resource.utils.ExceptionErrorMessage;

import java.util.*;
import static resource.utils.Utils.*;

@Service
@RequiredArgsConstructor
public class OrderServices implements IOrderService {

    private final IOrderRepository _orderRepository;
    private final IOrderMapper orderMapper;
    private final RestTemplate restTemplate;

    @Override
    public OrderResponseDto saveOrder(OrderDto orderDto) {
        Order order = orderMapper.orderDtoToOrder(orderDto);
        UUID customerId = orderDto.getUserId();
        try{
            if(!findCustomer(customerId)){
                throw new ExceptionErrorMessage("The customer was not found", true);
            }
        }catch (Exception x) {
            throw new ExceptionErrorMessage("The customer was not found or the service is unavailable", true);
        }

        order.setUserId(customerId);

        UUID shopId = orderDto.getShopId();
        try{
            if(!findShop(shopId)){
                throw new ExceptionErrorMessage("The shop was not found", true);
            }
        }catch (Exception x) {
            throw new ExceptionErrorMessage("The shop was not found", false);
        }
        order.setShopId(shopId);
        OrderItem orderItem;
        Sandwich sandwich;
        List<OrderItem> orderItemList = new ArrayList<>();
        HashMap<UUID, Integer> sandwichIntegerHashMap = new HashMap<>();

        for (OrderItemDto orderItemDto : orderDto.getOrderItemDto()) {
            orderItem = orderMapper.orderItemDtoToOrderItem(orderItemDto);

            sandwich = findSandwich(orderItemDto.getSandwichId());

            assert sandwich != null;
            orderItem.setSandwichId(sandwich.getId());
            sandwichIntegerHashMap.put(sandwich.getId(), orderItem.getQuantity());
            orderItemList.add(orderItem);
        }

        order.setOrderItems(orderItemList);

        List<LinkedHashMap> promotionTypes = findPromotionType();
        List<LinkedHashMap> availablePromotionsT = findAvailablePromotions(shopId, orderDto.getDeliveryDate());
        List<Promotion> availablePromotions = new ArrayList<>();
        for(LinkedHashMap linkedHashMap : availablePromotionsT){
            availablePromotions.add(promotionfromLinkedHashMap(linkedHashMap));
        }

        PROMOTIONKIND promotionkind;

        assert promotionTypes != null;
        if (promotionTypes.isEmpty()) {
            promotionkind = PROMOTIONKIND.CUMULATIVE;
        } else {
            promotionkind = promotionTypefromLinkedHashMap(promotionTypes.get(0)).getPromotionkind();
        }

        Promotion bestLocalPromotion;

        double totalPrice = 0;
        UUID sandwichTempId;
        if (promotionkind.equals(PROMOTIONKIND.CUMULATIVE)) {
            Promotion bestGlobalPromotion;
            for (Map.Entry<UUID, Integer> map : sandwichIntegerHashMap.entrySet()) {
                sandwichTempId = map.getKey();
                bestGlobalPromotion = null;
                bestLocalPromotion = null;
                for (Promotion promotion : availablePromotions) {
                    if (promotion.getSandwichIds().contains(sandwichTempId)) {
                        if (promotion.getId() == null) {
                            if (bestGlobalPromotion == null) {
                                bestGlobalPromotion = promotion;
                            } else if (promotion.getDiscountPercentage() > bestGlobalPromotion.getDiscountPercentage()) {
                                bestGlobalPromotion = promotion;
                            }
                        } else {
                            if(bestLocalPromotion == null){
                                bestLocalPromotion = promotion;
                            }
                            else if(promotion.getDiscountPercentage() > bestLocalPromotion.getDiscountPercentage()) {
                                bestLocalPromotion = promotion;
                            }
                        }
                    }
                }
                double globalPerc;
                if(bestGlobalPromotion == null){
                    globalPerc = 0;
                }else {
                    globalPerc = bestGlobalPromotion.getDiscountPercentage();
                }
                double localPerc;
                if(bestLocalPromotion == null){
                    localPerc = 0;
                }else {
                    localPerc = bestLocalPromotion.getDiscountPercentage();
                }

                Sandwich foundSandwich = findSandwich(sandwichTempId);

                if(globalPerc > localPerc) {
                    totalPrice += (foundSandwich.getPrice() * map.getValue()) * (1 - ((globalPerc/100 + (globalPerc/100 * localPerc/100))));
                }else{
                    totalPrice += (foundSandwich.getPrice() * map.getValue()) * (1 - ((localPerc/100 + (localPerc/100 * globalPerc/100))));
                }
            }
        }else{
            for (Map.Entry<UUID, Integer> map : sandwichIntegerHashMap.entrySet()) {
                bestLocalPromotion = null;
                sandwichTempId = map.getKey();
                for (Promotion promotion : availablePromotions) {
                    if (promotion.getSandwichIds().contains(sandwichTempId)) {
                            if(bestLocalPromotion == null){
                                bestLocalPromotion = promotion;
                            }
                            else if(promotion.getDiscountPercentage() > bestLocalPromotion.getDiscountPercentage()) {
                                bestLocalPromotion = promotion;
                            }
                    }
                }

                Sandwich foundSandwich = findSandwich(sandwichTempId);

                if(bestLocalPromotion != null) {
                    double localPerc = bestLocalPromotion.getDiscountPercentage();
                    totalPrice += (foundSandwich.getPrice() * map.getValue()) * (1 - (localPerc/100));
                }else{
                    totalPrice += (foundSandwich.getPrice() * map.getValue());
                }
            }
        }

        order.setTotalPrice(totalPrice);

        return orderMapper.orderToOrderResponseDto(_orderRepository.save(order));
    }

    private List<LinkedHashMap> findPromotionType(){
        ObjectMapper objectMapper = new ObjectMapper();

        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8090/promotion").build();
        GraphQLWebClient graphQLWebClient = GraphQLWebClient.newInstance(webClient, objectMapper);
        return (List<LinkedHashMap>) graphQLWebClient.post("getPromotionType.graphql", List.class).block();
    }

    private List<LinkedHashMap> findAvailablePromotions(UUID id, String date){
        ObjectMapper objectMapper = new ObjectMapper();

        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8090/promotion").build();
        GraphQLWebClient graphQLWebClient = GraphQLWebClient.newInstance(webClient, objectMapper);

        return (List<LinkedHashMap>) graphQLWebClient.post("getAvailablePromotions.graphql", Map.of("id", id, "deliveryDate", date), List.class).block();
    }

    private boolean findCustomer(UUID id){
        ObjectMapper objectMapper = new ObjectMapper();

        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8090/user").build();
        GraphQLWebClient graphQLWebClient = GraphQLWebClient.newInstance(webClient, objectMapper);
        return Boolean.TRUE.equals(graphQLWebClient.post("getUserCustomer.graphql", Map.of("id", id), Boolean.class).block());
    }

    private boolean findShop(UUID id){
        ObjectMapper objectMapper = new ObjectMapper();

        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8090/shop").build();
        GraphQLWebClient graphQLWebClient = GraphQLWebClient.newInstance(webClient, objectMapper);
        return Boolean.TRUE.equals(graphQLWebClient.post("findShopById.graphql", Map.of("id", id), Boolean.class).block());
    }

    private Sandwich findSandwich(UUID sandwichId){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UUID> requestSandwich = new HttpEntity<>(sandwichId,headers);

        return restTemplate.exchange("http://localhost:8095/" + sandwichId,
                HttpMethod.GET, requestSandwich, new ParameterizedTypeReference<Sandwich>() {}).getBody();
    }

    @Override
    public List<OrderResponseDto> getOrdersByUserId(UUID uuid) {

        List<OrderResponseDto> allOrdersDtoByUser = new ArrayList<>();
        List<Order> orders = _orderRepository.getUserOrders(uuid);

        for(Order order: orders){
            allOrdersDtoByUser.add(orderMapper.orderToOrderResponseDto(order));
        }

        return allOrdersDtoByUser;
    }

}
