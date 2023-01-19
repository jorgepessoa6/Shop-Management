package resource.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import resource.dto.ScheduleDto;
import resource.dto.ShopDto;
import resource.dto.StorageDto;
import resource.dto.responseDtos.ShopResponseDto;
import resource.mapper.IShopMapper;
import resource.model.Schedule;
import resource.model.Shop;
import resource.repository.IShopRepository;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class ShopServiceTest {

    @Mock
    IShopRepository shopRepository;

    @Mock
    IShopMapper modelMapper;

    Shop shop = new Shop();

    ShopResponseDto shopResponseDto = new ShopResponseDto();

    ShopDto shopDto = new ShopDto();

    ScheduleDto scheduleDto = new ScheduleDto();

    Schedule schedule = new Schedule();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void updateShop() {
        shopDto.setSchedules(new ArrayList<>());
        when(shopRepository.save(shop)).thenReturn(shop);
        when(shopRepository.findById(shop.getId())).thenReturn(Optional.ofNullable(shop));
        when(modelMapper.scheduleDtoToSchedule(scheduleDto)).thenReturn(schedule);
        when(modelMapper.shopToShopResponseDto(shop)).thenReturn(shopResponseDto);
        ShopService shopService = new ShopService(shopRepository,null, modelMapper);
        assertEquals(shopService.updateShop(shopDto),shopResponseDto);
    }

    @Test
    void checkShopById() {
        when(shopRepository.existsById(shop.getId())).thenReturn(true);
        when(modelMapper.scheduleDtoToSchedule(scheduleDto)).thenReturn(schedule);
        when(modelMapper.shopToShopResponseDto(shop)).thenReturn(shopResponseDto);
        ShopService shopService = new ShopService(shopRepository,null, modelMapper);
        assertTrue(shopService.checkShopById(shop.getId()));
    }
}