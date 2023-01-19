package resource.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import resource.dto.OrderDto;
import resource.dto.responseDto.OrderResponseDto;
import resource.service.interfaces.IOrderService;

import javax.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController @RequiredArgsConstructor
@Validated
public class OrderController {
    private final IOrderService _orderServices;

    @MutationMapping
    public OrderResponseDto addOrder(@Argument @Valid OrderDto orderDto) {
        return _orderServices.saveOrder(orderDto);
    }

    @QueryMapping
    public List<OrderResponseDto> getOrderByUser(@Argument @Valid UUID id) {
        return _orderServices.getOrdersByUserId(id);
    }
}
