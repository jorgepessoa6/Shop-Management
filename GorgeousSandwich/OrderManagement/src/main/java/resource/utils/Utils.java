package resource.utils;

import resource.model.external.PROMOTIONKIND;
import resource.model.external.Promotion;
import resource.model.external.PromotionType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utils {

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    public static PromotionType promotionTypefromLinkedHashMap(LinkedHashMap map) {
        UUID id = UUID.fromString((String) map.get("id"));
        PROMOTIONKIND promotionkind = PROMOTIONKIND.valueOf((String) map.get("promotionkind"));
        return new PromotionType(id, promotionkind);
    }

    public static Promotion promotionfromLinkedHashMap(LinkedHashMap map) {
        UUID shopId = null;
        if(map.get("shopId") != null)
            shopId = UUID.fromString((String) map.get("shopId"));

        Date startDate = null;
        Date endDate = null;
        try {
            startDate = dateFormat.parse((String) map.get("startDate"));
            endDate = dateFormat.parse((String) map.get("endDate"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        double discount = (double) map.get("discountPercentage");
        List<String> sandwichIds = (List<String>) map.get("sandwichIds");
        List<UUID> sandwiches = new ArrayList<>();
        for(String str : sandwichIds){
            sandwiches.add(UUID.fromString(str));
        }
        return new Promotion(shopId, startDate, endDate, discount, shopId, sandwiches);
    }
}
