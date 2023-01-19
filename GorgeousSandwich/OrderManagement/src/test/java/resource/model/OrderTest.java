package resource.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

    private final UUID shopId = UUID.randomUUID();
    private final UUID userId = UUID.randomUUID();
    Order order = new Order();

    @Test
    void getId() {
        UUID id = new UUID(1, 1);
        order.setId(id);
        assertEquals(id, order.getId());
    }

    @Test
    void getDeliveryDate() {
        Date deliveryDate = new Date();
        order.setDeliveryDate(deliveryDate);
        assertEquals(deliveryDate, order.getDeliveryDate());
    }

    @Test
    void getTotalPrice() {
        double totalPrice = 500;
        order.setTotalPrice(totalPrice);
        assertEquals(totalPrice, order.getTotalPrice());
    }

    @Test
    void getShop() {
        order.setShopId(shopId);
        assertEquals(shopId, order.getShopId());
    }

    @Test
    void getOrderItems() {
        List<OrderItem> orderItemList = new ArrayList<>();
        order.setOrderItems(orderItemList);
        assertEquals(orderItemList, order.getOrderItems());
    }

    @Test
    void getUser() {
        order.setUserId(userId);
        assertEquals(userId, order.getUserId());
    }

    @Test
    void setId() {
        UUID id = new UUID(1, 1);
        order.setId(id);
        assertEquals(id, order.getId());
    }

    @Test
    void setDeliveryDate() {
        Date deliveryDate = new Date();
        order.setDeliveryDate(deliveryDate);
        assertEquals(deliveryDate, order.getDeliveryDate());
    }

    @Test
    void setTotalPrice() {
        double totalPrice = 500;
        order.setTotalPrice(totalPrice);
        assertEquals(totalPrice, order.getTotalPrice());
    }

    @Test
    void setShop() {
        order.setShopId(shopId);
        assertEquals(shopId, order.getShopId());
    }

    @Test
    void setOrderItems() {
        List<OrderItem> orderItemList = new ArrayList<>();
        order.setOrderItems(orderItemList);
        assertEquals(orderItemList, order.getOrderItems());
    }

    @Test
    void setUser() {
        order.setUserId(userId);
        assertEquals(userId, order.getUserId());
    }
}