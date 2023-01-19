package resource.service.interfaces;

import resource.dto.OrderDto;
import resource.dto.PromotionDto;
import resource.dto.responseDtos.PromotionResponseDto;
import resource.model.external.Order;

import java.util.List;

public interface IPromotionService {

    PromotionResponseDto savePromotion(PromotionDto promotionDTO);

    List<PromotionResponseDto> availablePromotions(OrderDto order);
}
