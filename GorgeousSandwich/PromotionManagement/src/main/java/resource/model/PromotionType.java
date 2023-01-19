package resource.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
public class PromotionType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private PROMOTIONKIND promotionkind;

    public void switchPromotionType(){
        if(promotionkind == PROMOTIONKIND.CUMULATIVE){
            promotionkind = PROMOTIONKIND.NONCUMULATIVE;
            return;
        }
        promotionkind = PROMOTIONKIND.CUMULATIVE;
    }
}
