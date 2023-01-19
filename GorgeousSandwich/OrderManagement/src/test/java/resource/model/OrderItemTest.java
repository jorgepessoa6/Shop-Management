package resource.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderItemTest {

    OrderItem orderItem = new OrderItem();

    private final UUID sandwichId = UUID.randomUUID();
    private final UUID newSandwichId = UUID.randomUUID();

    @Test
    void getId() {
        UUID id = new UUID(1, 1);
        orderItem.setId(id);
        assertEquals(id, orderItem.getId());
    }

    @Test
    void getQuantity() {
        int quantity = 10;
        orderItem.setQuantity(quantity);
        assertEquals(quantity, orderItem.getQuantity());
    }

    @Test
    void getSandwich() {
        orderItem.setSandwichId(sandwichId);
        assertEquals(sandwichId, orderItem.getSandwichId());
    }

    @Test
    void setId() {
        UUID id = new UUID(1, 1);
        orderItem.setId(id);
        assertEquals(id, orderItem.getId());
    }

    @Test
    void setQuantity() {
        int quantity = 10;
        orderItem.setQuantity(quantity);
        assertEquals(quantity, orderItem.getQuantity());
    }

    @Test
    void setSandwich() {
        orderItem.setSandwichId(newSandwichId);
        assertEquals(newSandwichId, orderItem.getSandwichId());
    }
}