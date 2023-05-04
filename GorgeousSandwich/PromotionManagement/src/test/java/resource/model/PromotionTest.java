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
    public void getIdTest() {
        UUID id = new UUID(1, 1);
        promotion.setId(id);
        assertEquals(id, promotion.getId());
    }

    @Test
    public void setIdTest() {
        UUID id = new UUID(1, 1);
        promotion.setId(id);
        assertEquals(id, promotion.getId());
    }

    @Test
    public void getStartDateTest() {
        Date startDate = new Date();
        promotion.setStartDate(startDate);
        assertEquals(startDate, promotion.getStartDate());
    }

    @Test
    public void setStartDateTest() {
        Date startDate = new Date();
        promotion.setStartDate(startDate);
        assertEquals(startDate, promotion.getStartDate());
    }

    @Test
    public void getEndDateTest() {
        Date endDate = new Date();
        promotion.setEndDate(endDate);
        assertEquals(endDate, promotion.getEndDate());
    }

    @Test
    public void setEndDateTest() {
        Date endDate = new Date();
        promotion.setEndDate(endDate);
        assertEquals(endDate, promotion.getEndDate());
    }

    @Test
    public void getDiscountPercentageTest() {
        double discountPercentage = 20;
        promotion.setDiscountPercentage(discountPercentage);
        assertEquals(discountPercentage, promotion.getDiscountPercentage());
    }

    @Test
    public void setDiscountPercentageTest() {
        double discountPercentage = 20;
        promotion.setDiscountPercentage(discountPercentage);
        assertEquals(discountPercentage, promotion.getDiscountPercentage());
    }

    @Test
    public void getShopTest() {
        promotion.setShopId(shopId);
        assertEquals(shopId, promotion.getShopId());
    }

    @Test
    public void setShopTest() {
        promotion.setShopId(shopId);
        assertEquals(shopId, promotion.getShopId());
    }

    @Test
    public void getSandwichesTest() {
        List<UUID> sandwichList = new ArrayList<>();
        promotion.setSandwichIds(sandwichList);
        assertEquals(sandwichList, promotion.getSandwichIds());
    }

    @Test
    public void setSandwichesTest() {
        List<UUID> sandwichList = new ArrayList<>();
        promotion.setSandwichIds(sandwichList);
        assertEquals(sandwichList, promotion.getSandwichIds());
    }
}