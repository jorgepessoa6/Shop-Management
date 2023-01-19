package resource.model;

import org.junit.jupiter.api.Test;
import resource.model.external.Sandwich;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PromotionTest {

    private final UUID shopId = UUID.randomUUID();
    Promotion promotion = new Promotion();
    @Test
    void getId() {
        UUID id = new UUID(1, 1);
        promotion.setId(id);
        assertEquals(id, promotion.getId());
    }

    @Test
    void setId() {
        UUID id = new UUID(1, 1);
        promotion.setId(id);
        assertEquals(id, promotion.getId());
    }

    @Test
    void getStartDate() {
        Date startDate = new Date();
        promotion.setStartDate(startDate);
        assertEquals(startDate, promotion.getStartDate());
    }

    @Test
    void setStartDate() {
        Date startDate = new Date();
        promotion.setStartDate(startDate);
        assertEquals(startDate, promotion.getStartDate());
    }

    @Test
    void getEndDate() {
        Date endDate = new Date();
        promotion.setEndDate(endDate);
        assertEquals(endDate, promotion.getEndDate());
    }

    @Test
    void setEndDate() {
        Date endDate = new Date();
        promotion.setEndDate(endDate);
        assertEquals(endDate, promotion.getEndDate());
    }

    @Test
    void getDiscountPercentage() {
        double discountPercentage = 20;
        promotion.setDiscountPercentage(discountPercentage);
        assertEquals(discountPercentage, promotion.getDiscountPercentage());
    }

    @Test
    void setDiscountPercentage() {
        double discountPercentage = 20;
        promotion.setDiscountPercentage(discountPercentage);
        assertEquals(discountPercentage, promotion.getDiscountPercentage());
    }

    @Test
    void getShop() {
        promotion.setShopId(shopId);
        assertEquals(shopId, promotion.getShopId());
    }

    @Test
    void setShop() {
        promotion.setShopId(shopId);
        assertEquals(shopId, promotion.getShopId());
    }

    @Test
    void getSandwiches() {
        List<UUID> sandwichList = new ArrayList<>();
        promotion.setSandwichIds(sandwichList);
        assertEquals(sandwichList, promotion.getSandwichIds());
    }

    @Test
    void setSandwiches() {
        List<UUID> sandwichList = new ArrayList<>();
        promotion.setSandwichIds(sandwichList);
        assertEquals(sandwichList, promotion.getSandwichIds());
    }
}