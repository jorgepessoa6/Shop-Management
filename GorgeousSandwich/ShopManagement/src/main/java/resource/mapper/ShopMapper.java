package resource.mapper;

import org.springframework.stereotype.Component;
import resource.dto.ScheduleDto;
import resource.dto.ShopDto;
import resource.dto.StorageDto;
import resource.dto.responseDtos.ScheduleResponseDto;
import resource.dto.responseDtos.ShopResponseDto;
import resource.dto.responseDtos.StorageResponseDto;
import resource.model.DAYOFTHEWEEK;
import resource.model.Schedule;
import resource.model.Shop;
import resource.model.Storage;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShopMapper implements IShopMapper {

    @Override
    public Storage storageDtoToStorage(StorageDto storageDto) {
        Storage storage = new Storage();
        storage.setQuantity(storageDto.getQuantity());
        return storage;
    }

    @Override
    public Shop shopDtoToShop(ShopDto shopDto) {
        Shop shop = new Shop();
        shop.setAddress(shopDto.getAddress());
        shop.setDesignation(shopDto.getDesignation());

        return shop;
    }

    @Override
    public ShopResponseDto shopToShopResponseDto(Shop shop) {
        ShopResponseDto shopResponseDto = new ShopResponseDto();

        shopResponseDto.setAddress(shop.getAddress());
        shopResponseDto.setDesignation(shop.getDesignation());

        List<ScheduleResponseDto> scheduleDtoList = new ArrayList<>();
        for (Schedule s : shop.getSchedules()) {
            scheduleDtoList.add(scheduleToScheduleResponseDto(s));
        }
        shopResponseDto.setSchedules(scheduleDtoList);

        shopResponseDto.setPersonInChargeId(shop.getPersonInChargeId());

        List<StorageResponseDto> storageDtoList = new ArrayList<>();

        for (Storage s : shop.getStorages()) {
            storageDtoList.add(storageToStorageResponseDto(s));
        }

        shopResponseDto.setStorages(storageDtoList);
        return shopResponseDto;
    }

    private StorageResponseDto storageToStorageResponseDto(Storage s) {
        StorageResponseDto storageResponseDto = new StorageResponseDto();
        storageResponseDto.setQuantity(s.getQuantity());
        storageResponseDto.setSandwichId(s.getSandwichId());
        return storageResponseDto;
    }

    @Override
    public Schedule scheduleDtoToSchedule(ScheduleDto scheduleDto) {
        Schedule schedule = new Schedule();
        schedule.setClosingHours(scheduleDto.getClosingHours());
        schedule.setOpeningHours(scheduleDto.getOpeningHours());
        schedule.setDayOfTheWeek(DAYOFTHEWEEK.valueOf(scheduleDto.getDayOfWeek()));
        return schedule;
    }

    @Override
    public ScheduleResponseDto scheduleToScheduleResponseDto(Schedule s) {
        ScheduleResponseDto scheduleDto = new ScheduleResponseDto();
        scheduleDto.setClosingHours(s.getClosingHours());
        scheduleDto.setOpeningHours(s.getOpeningHours());
        scheduleDto.setDayOfWeek(s.getDayOfTheWeek());
        return scheduleDto;
    }

}
