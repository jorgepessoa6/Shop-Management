package resource.mapper;

import resource.dto.PromotionDto;
import resource.dto.responseDtos.PromotionResponseDto;
import resource.dto.responseDtos.PromotionTypeResponseDto;
import resource.model.Promotion;
import resource.model.PromotionType;

public interface IPromotionMapper {

    Promotion promotionDtoToPromotion(PromotionDto promotionDto);
    PromotionResponseDto promotionToPromotionResponseDto(Promotion promotion);
    PromotionTypeResponseDto promotionTypeToPromotionResponseTypeDto(PromotionType promotionType);

}
