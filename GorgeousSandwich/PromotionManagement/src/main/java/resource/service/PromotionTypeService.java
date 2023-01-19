package resource.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import resource.dto.responseDtos.PromotionTypeResponseDto;
import resource.mapper.IPromotionMapper;
import resource.model.PROMOTIONKIND;
import resource.model.PromotionType;
import resource.repository.IPromotionTypeRepository;
import resource.service.interfaces.IPromotionTypeService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PromotionTypeService implements IPromotionTypeService {

    private final IPromotionTypeRepository promotionTypeRepository;
    private final IPromotionMapper promotionMapper;

    @Override
    public PromotionTypeResponseDto switchProductionType() {

        List<PromotionType> promotionTypeList = promotionTypeRepository.findAll();

        PromotionType promotionType;
        if(promotionTypeList.isEmpty()){
            promotionType = new PromotionType();
            promotionType.setPromotionkind(PROMOTIONKIND.CUMULATIVE);
        }else{
            promotionType = promotionTypeList.get(0);
            promotionType.switchPromotionType();
        }

        return promotionMapper.promotionTypeToPromotionResponseTypeDto(promotionTypeRepository.save(promotionType));
    }

    @Override
    public List<PromotionType> findPromotionTypes() {
        return promotionTypeRepository.findPromotionTypes();
    }
}
