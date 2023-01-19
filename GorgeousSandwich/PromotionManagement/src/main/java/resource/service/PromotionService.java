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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import resource.dto.OrderDto;
import resource.dto.PromotionDto;
import resource.dto.responseDtos.PromotionResponseDto;
import resource.mapper.IPromotionMapper;
import resource.model.Promotion;
import resource.model.external.Sandwich;
import resource.repository.IPromotionRepository;
import resource.service.interfaces.IPromotionService;
import resource.utils.ExceptionErrorMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class PromotionService implements IPromotionService {
    private final IPromotionRepository promotionRepository;
    private final IPromotionMapper promotionMapper;
    private final RestTemplate restTemplate;

    @Override
    public PromotionResponseDto savePromotion(PromotionDto promotionDTO) {
        List<UUID> sandwichesList = promotionDTO.getSandwiches();

        Promotion promotion = promotionMapper.promotionDtoToPromotion(promotionDTO);

        List<UUID> responseSandwichIds = new ArrayList<>();
        boolean checkShopExist;
        boolean validSandwiches = true;
        try{
            if(promotionDTO.getShopId() == null) {
                checkShopExist = true;
            }else {
                checkShopExist = findShop(promotionDTO.getShopId());
            }
            for(UUID id : sandwichesList){
                findSandwich(id);
                responseSandwichIds.add(id);
            }
        }catch (Exception x){
            throw new ExceptionErrorMessage("Either the shop or some sandwiches don't exist", true);
        }
        promotion.setSandwichIds(responseSandwichIds);
        if(!checkShopExist || !validSandwiches){
            throw new NoSuchElementException("Either the shop or some sandwiches don't exist");
        }

        if(promotionDTO.getShopId() != null) {
            promotion.setShopId(promotionDTO.getShopId());
        }

        return promotionMapper.promotionToPromotionResponseDto(promotionRepository.save(promotion));
    }

    private boolean findShop(UUID id){
        ObjectMapper objectMapper = new ObjectMapper();

        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8090/shop").build();
        GraphQLWebClient graphQLWebClient = GraphQLWebClient.newInstance(webClient, objectMapper);
        return Boolean.TRUE.equals(graphQLWebClient.post("findShopId.graphql", Map.of("id", id), Boolean.class).block());
    }

    private void findSandwich(UUID sandwichId){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UUID> requestSandwich = new HttpEntity<>(sandwichId,headers);

        restTemplate.exchange("http://localhost:8095/" + sandwichId,
                HttpMethod.GET, requestSandwich, new ParameterizedTypeReference<Sandwich>() {}).getBody();
    }

    @Override
    public List<PromotionResponseDto> availablePromotions(OrderDto order) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date;
        try {
            date = dateFormat.parse(order.getDeliveryDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        List<Promotion> availablePromotions;
        try{
            availablePromotions = promotionRepository.getAvailablePromotions(date, order.getShopId());
            System.out.println(availablePromotions);
        }
        catch (Exception x){
            throw new ExceptionErrorMessage("Some error occurred. Check your shop id and deliveryDate", false);
        }

        List<PromotionResponseDto> availablePromotionsDto = new ArrayList<>();
        for (Promotion p : availablePromotions) {
            availablePromotionsDto.add(promotionMapper.promotionToPromotionResponseDto(p));
        }

        return availablePromotionsDto;
    }
}
