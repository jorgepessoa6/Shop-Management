package resource.mapper;

import org.springframework.stereotype.Component;
import resource.dto.OrderDto;
import resource.dto.OrderItemDto;
import resource.dto.responseDto.OrderItemResponseDto;
import resource.dto.responseDto.OrderResponseDto;
import resource.model.Order;
import resource.model.OrderItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class OrderMapper implements IOrderMapper{

    @Override
    public OrderResponseDto orderToOrderResponseDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setTotalPrice(order.getTotalPrice());
        orderResponseDto.setDeliveryDate(order.getDeliveryDate());
        List<OrderItemResponseDto> orderItemResponseDtoList = new ArrayList<>();
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItemResponseDtoList.add(orderItemToOrderItemResponseDto(orderItem));
        }
        orderResponseDto.setOrderItems(orderItemResponseDtoList);
        return orderResponseDto;
    }

    @Override
    public Order orderDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setDeliveryDate(new Date());
        return order;
    }

    @Override
    public OrderItem orderItemDtoToOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(orderItemDto.getQuantity());
        return orderItem;
    }

    @Override
    public OrderItemResponseDto orderItemToOrderItemResponseDto(OrderItem orderItem) {
        OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto();
        orderItemResponseDto.setId(orderItem.getId());
        orderItemResponseDto.setQuantity(orderItem.getQuantity());
        orderItemResponseDto.setSandwichId(orderItem.getSandwichId());
        return orderItemResponseDto;
    }
}
