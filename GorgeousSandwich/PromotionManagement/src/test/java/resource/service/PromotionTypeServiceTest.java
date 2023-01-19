package resource.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import resource.dto.responseDtos.PromotionTypeResponseDto;
import resource.mapper.IPromotionMapper;
import resource.model.PROMOTIONKIND;
import resource.model.PromotionType;
import resource.repository.IPromotionTypeRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PromotionTypeServiceTest {

    @Mock
    private IPromotionTypeRepository promotionTypeRepository;

    @Mock
    private IPromotionMapper modelMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void switchProductionTypeNewPromotionType() {
        PromotionType promotionType = new PromotionType();
        promotionType.setPromotionkind(PROMOTIONKIND.CUMULATIVE);
        PromotionTypeResponseDto promotionTypeResponseDto = new PromotionTypeResponseDto();
        promotionTypeResponseDto.setPromotionKind(PROMOTIONKIND.CUMULATIVE.toString());

        when(promotionTypeRepository.findAll()).thenReturn(new ArrayList<>());
        when(promotionTypeRepository.save(promotionType)).thenReturn(promotionType);
        when(modelMapper.promotionTypeToPromotionResponseTypeDto(any())).thenReturn(promotionTypeResponseDto);

        PromotionTypeService promotionTypeService = new PromotionTypeService(promotionTypeRepository, modelMapper);
        promotionTypeResponseDto = promotionTypeService.switchProductionType();

        assertEquals(PROMOTIONKIND.CUMULATIVE.toString(), promotionTypeResponseDto.getPromotionKind());
    }

    @Test
    void switchProductionTypeToCumulative() {

        PromotionType promotionType = new PromotionType();
        promotionType.setPromotionkind(PROMOTIONKIND.NONCUMULATIVE);
        List<PromotionType> promotionTypeList = new ArrayList<>();
        promotionTypeList.add(promotionType);
        when(promotionTypeRepository.findAll()).thenReturn(promotionTypeList);
        when(promotionTypeRepository.save(promotionType)).thenReturn(promotionType);

        PromotionTypeResponseDto promotionTypeResponseDto = new PromotionTypeResponseDto();
        promotionTypeResponseDto.setPromotionKind(PROMOTIONKIND.CUMULATIVE.toString());
        when(modelMapper.promotionTypeToPromotionResponseTypeDto(promotionType)).thenReturn(promotionTypeResponseDto);
        PromotionTypeService promotionTypeService = new PromotionTypeService(promotionTypeRepository, modelMapper);

        promotionTypeResponseDto = promotionTypeService.switchProductionType();

        assertEquals(PROMOTIONKIND.CUMULATIVE.toString(), promotionTypeResponseDto.getPromotionKind());
    }

    @Test
    void switchProductionTypeToNonCumulative() {

        PromotionType promotionType = new PromotionType();
        promotionType.setPromotionkind(PROMOTIONKIND.CUMULATIVE);
        List<PromotionType> promotionTypeList = new ArrayList<>();
        promotionTypeList.add(promotionType);
        when(promotionTypeRepository.findAll()).thenReturn(promotionTypeList);
        when(promotionTypeRepository.save(promotionType)).thenReturn(promotionType);

        PromotionTypeResponseDto promotionTypeResponseDto = new PromotionTypeResponseDto();
        promotionTypeResponseDto.setPromotionKind(PROMOTIONKIND.NONCUMULATIVE.toString());
        when(modelMapper.promotionTypeToPromotionResponseTypeDto(promotionType)).thenReturn(promotionTypeResponseDto);
        PromotionTypeService promotionTypeService = new PromotionTypeService(promotionTypeRepository, modelMapper);

        promotionTypeResponseDto = promotionTypeService.switchProductionType();

        assertEquals(PROMOTIONKIND.NONCUMULATIVE.toString(), promotionTypeResponseDto.getPromotionKind());
    }
}