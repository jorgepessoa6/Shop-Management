package resource.service.interfaces;

import resource.dto.OrderDto;
import resource.dto.responseDto.OrderResponseDto;

import java.util.List;
import java.util.UUID;

public interface IOrderService {

    OrderResponseDto saveOrder(OrderDto orderDto);

    List<OrderResponseDto> getOrdersByUserId(UUID uuid);

}
