package resource.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PromotionTypeTest {

    private final PromotionType promotionType = new PromotionType();

    @Test
    void switchPromotionTypeToNonCumulative() {
        promotionType.setPromotionkind(PROMOTIONKIND.CUMULATIVE);

        promotionType.switchPromotionType();
        assertEquals(PROMOTIONKIND.NONCUMULATIVE, promotionType.getPromotionkind());
    }

    @Test
    void switchPromotionTypeToCumulative() {
        promotionType.setPromotionkind(PROMOTIONKIND.NONCUMULATIVE);

        promotionType.switchPromotionType();
        assertEquals(PROMOTIONKIND.CUMULATIVE, promotionType.getPromotionkind());
    }

    @Test
    void getId() {
        UUID id = new UUID(1, 1);
        promotionType.setId(id);
        assertEquals(id, promotionType.getId());
    }

    @Test
    void getPromotionKind() {
        promotionType.setPromotionkind(PROMOTIONKIND.NONCUMULATIVE);

        assertEquals(PROMOTIONKIND.NONCUMULATIVE, promotionType.getPromotionkind());
    }

    @Test
    void setId() {
        UUID id = new UUID(1, 1);
        promotionType.setId(id);
        assertEquals(id, promotionType.getId());
    }

    @Test
    void setPromotionKind() {
        promotionType.setPromotionkind(PROMOTIONKIND.NONCUMULATIVE);

        assertEquals(PROMOTIONKIND.NONCUMULATIVE, promotionType.getPromotionkind());
    }
}