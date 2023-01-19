package resource.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import resource.dto.responseDtos.PromotionTypeResponseDto;
import resource.model.PromotionType;
import resource.service.interfaces.IPromotionTypeService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PromotionTypeController {
    private final IPromotionTypeService promotionTypeService;

    @MutationMapping
    public PromotionTypeResponseDto switchPromotionType(){
        return promotionTypeService.switchProductionType();
    }

    @QueryMapping
    public List<PromotionType> findPromotionType() {
        return promotionTypeService.findPromotionTypes();
    }
}
