package resource.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import resource.dto.OrderDto;
import resource.dto.PromotionDto;
import resource.dto.responseDtos.PromotionResponseDto;
import resource.service.interfaces.IPromotionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Validated
public class PromotionController {

    private final IPromotionService promotionService;

    @MutationMapping
    public PromotionResponseDto addPromotion(@Argument @Valid PromotionDto promotion) {
        return promotionService.savePromotion(promotion);
    }

    @QueryMapping
    public List<PromotionResponseDto> availablePromotions(@Argument @Valid OrderDto orderDto) {
        return promotionService.availablePromotions(orderDto);
    }
}
