package resource.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StorageTest {

    private final Storage storage = new Storage();

    private final UUID sandwichId = UUID.randomUUID();
    private final UUID newSandwichId = UUID.randomUUID();

    @Test
    void getId() {
        UUID id = new UUID(1, 1);
        storage.setId(id);
        assertEquals(id, storage.getId());
    }

    @Test
    void getQuantity() {
        storage.setQuantity(12);
        assertEquals(12, storage.getQuantity());
    }

    @Test
    void getSandwich() {
        storage.setSandwichId(sandwichId);
        assertNotNull(storage.getSandwichId());
    }

    @Test
    void setId() {
        UUID id = new UUID(1, 1);
        storage.setId(id);
        assertEquals(id, storage.getId());
    }

    @Test
    void setQuantity() {
        storage.setQuantity(12);
        assertEquals(12, storage.getQuantity());
    }

    @Test
    void setSandwich() {
        storage.setSandwichId(newSandwichId);
        assertNotNull(storage.getSandwichId());
    }
}