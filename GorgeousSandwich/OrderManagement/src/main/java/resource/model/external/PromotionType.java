package resource.model.external;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PromotionType {

    private UUID id;

    private PROMOTIONKIND promotionkind;


    public PromotionType(UUID id, PROMOTIONKIND promotionkind) {
        this.id = id;
        this.promotionkind = promotionkind;
    }

}
