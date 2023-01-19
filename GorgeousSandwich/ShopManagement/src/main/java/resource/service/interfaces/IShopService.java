package resource.service.interfaces;

import resource.dto.ShopDto;
import resource.dto.StorageDto;
import resource.dto.responseDtos.ShopResponseDto;

import java.util.UUID;

public interface IShopService {

    ShopResponseDto saveShop(ShopDto shopDto);
    ShopResponseDto updateShop(ShopDto shopDto);
    ShopResponseDto updateShopStorage(StorageDto storageDto);

    boolean checkShopById(UUID shopId);
}
