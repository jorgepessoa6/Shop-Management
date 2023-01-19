package resource.mapper;

import org.springframework.stereotype.Component;
import resource.dto.PromotionDto;
import resource.dto.responseDtos.PromotionResponseDto;
import resource.dto.responseDtos.PromotionTypeResponseDto;
import resource.model.Promotion;
import resource.model.PromotionType;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class PromotionMapper implements IPromotionMapper{

    @Override
    public Promotion promotionDtoToPromotion(PromotionDto promotionDto) {
        Promotion promotion = new Promotion();
        promotion.setStartDate(new Date());
        promotion.setEndDate(new Date());
        promotion.setDiscountPercentage(promotionDto.getDiscountPercentage());

        return promotion;
    }

    @Override
    public PromotionResponseDto promotionToPromotionResponseDto(Promotion promotion) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        PromotionResponseDto promotionResponseDto = new PromotionResponseDto();
        promotionResponseDto.setId(promotion.getId());
        promotionResponseDto.setStartDate(dateFormat.format(promotion.getStartDate()));
        promotionResponseDto.setEndDate(dateFormat.format(promotion.getEndDate()));
        promotionResponseDto.setDiscountPercentage(promotion.getDiscountPercentage());
        promotionResponseDto.setShopId(promotion.getShopId());
        promotionResponseDto.setSandwichIds(promotion.getSandwichIds());
        System.out.println(promotionResponseDto.toString());
        return promotionResponseDto;
    }

    @Override
    public PromotionTypeResponseDto promotionTypeToPromotionResponseTypeDto(PromotionType promotionType) {
        PromotionTypeResponseDto promotionTypeResponseDto = new PromotionTypeResponseDto();
        promotionTypeResponseDto.setPromotionKind(promotionType.getPromotionkind().toString());

        return promotionTypeResponseDto;
    }
}
