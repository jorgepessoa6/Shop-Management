package resource.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import resource.dto.OrderDto;
import resource.dto.PromotionDto;
import resource.dto.responseDtos.PromotionResponseDto;
import resource.mapper.IPromotionMapper;
import resource.model.Promotion;
import resource.model.external.Sandwich;
import resource.repository.IPromotionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PromotionServiceTest {

    @Mock
    IPromotionRepository promotionRepository;

    @Mock
    IPromotionMapper modelMapper;

    PromotionDto promotionDto = new PromotionDto();

    PromotionResponseDto promotionResponseDto = new PromotionResponseDto();

    Promotion promotion = new Promotion();

    Sandwich sandwich = new Sandwich();

    List<Sandwich> sandwichList = new ArrayList<>();

    List<UUID> uuids = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void availablePromotions() {
        uuids.add(sandwich.getId());
        promotionDto.setSandwiches(uuids);
        sandwichList.add(sandwich);
        OrderDto orderDto = new OrderDto();
        orderDto.setShopId(new UUID(1, 1));
        orderDto.setDeliveryDate("21/03/2020 02:20");
        List<PromotionResponseDto> list = new ArrayList<>();

        when(promotionRepository.save(promotion)).thenReturn(promotion);
        when(modelMapper.promotionDtoToPromotion(promotionDto)).thenReturn(promotion);
        when(modelMapper.promotionToPromotionResponseDto(promotion)).thenReturn(promotionResponseDto);
        PromotionService promotionService = new PromotionService(promotionRepository,modelMapper, null);
        assertEquals(promotionService.availablePromotions(orderDto),list);
    }
}