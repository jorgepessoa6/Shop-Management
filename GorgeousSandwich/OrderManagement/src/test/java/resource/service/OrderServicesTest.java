package resource.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import resource.dto.OrderDto;
import resource.dto.OrderItemDto;
import resource.dto.responseDto.OrderResponseDto;
import resource.mapper.IOrderMapper;
import resource.model.Order;
import resource.model.OrderItem;
import resource.model.external.Promotion;
import resource.model.external.PromotionType;
import resource.model.external.Sandwich;
import resource.repository.IOrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class OrderServicesTest {

    @Mock
    IOrderMapper modelMapper;

    @Mock
    IOrderRepository orderRepository;

    OrderResponseDto orderResponseDto = new OrderResponseDto();

    OrderResponseDto orderResponseDto2 = new OrderResponseDto();

    Order order = new Order();

    Order order2 = new Order();

    List<Order> orders = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void getOrdersByUserId() {
        orders.add(order);
        orders.add(order2);
        UUID uuid = new UUID(1,1);
        when(orderRepository.getUserOrders(uuid)).thenReturn(orders);
        when(modelMapper.orderToOrderResponseDto(order)).thenReturn(orderResponseDto);
        when(modelMapper.orderToOrderResponseDto(order2)).thenReturn(orderResponseDto2);

        OrderServices orderServices = new OrderServices(orderRepository, modelMapper, null);

        List<OrderResponseDto> orderResponseDtoList = orderServices.getOrdersByUserId(uuid);

        assertEquals(orders.size(), orderResponseDtoList.size());
        assertEquals(orderResponseDto, orderResponseDtoList.get(0));
        assertEquals(orderResponseDto2, orderResponseDtoList.get(1));
    }
}