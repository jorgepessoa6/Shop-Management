package resource.service.interfaces;


import resource.dto.responseDtos.PromotionTypeResponseDto;
import resource.model.PromotionType;

import java.util.List;

public interface IPromotionTypeService {

    PromotionTypeResponseDto switchProductionType();

    List<PromotionType> findPromotionTypes();
}
