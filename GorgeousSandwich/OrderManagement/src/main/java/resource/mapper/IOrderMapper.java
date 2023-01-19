package resource.mapper;

import resource.dto.OrderDto;
import resource.dto.OrderItemDto;
import resource.dto.responseDto.OrderItemResponseDto;
import resource.dto.responseDto.OrderResponseDto;
import resource.model.Order;
import resource.model.OrderItem;

public interface IOrderMapper {

    Order orderDtoToOrder(OrderDto orderDto);

    OrderItem orderItemDtoToOrderItem(OrderItemDto orderItemDto);

    OrderItemResponseDto orderItemToOrderItemResponseDto(OrderItem orderItem);

    OrderResponseDto orderToOrderResponseDto(Order order);
}
