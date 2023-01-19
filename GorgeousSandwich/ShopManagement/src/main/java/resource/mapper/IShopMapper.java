package resource.mapper;

import resource.dto.ScheduleDto;
import resource.dto.ShopDto;
import resource.dto.StorageDto;
import resource.dto.responseDtos.ScheduleResponseDto;
import resource.dto.responseDtos.ShopResponseDto;
import resource.model.Schedule;
import resource.model.Shop;
import resource.model.Storage;

public interface IShopMapper {

    Shop shopDtoToShop(ShopDto shopDto);
    ShopResponseDto shopToShopResponseDto(Shop shop);
    Storage storageDtoToStorage(StorageDto storageDto);
    ScheduleResponseDto scheduleToScheduleResponseDto(Schedule s);
    Schedule scheduleDtoToSchedule(ScheduleDto scheduleDto);

}
