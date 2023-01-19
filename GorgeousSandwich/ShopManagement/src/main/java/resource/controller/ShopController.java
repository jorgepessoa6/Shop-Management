package resource.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.validation.annotation.Validated;
import resource.service.interfaces.IShopService;
import org.springframework.web.bind.annotation.*;
import resource.dto.ShopDto;
import resource.dto.StorageDto;
import resource.dto.responseDtos.ShopResponseDto;

import javax.validation.Valid;
import java.util.UUID;


@Controller
@RequiredArgsConstructor
@Validated
public class ShopController {
    private final IShopService services;

    @MutationMapping
    public ShopResponseDto addShop(@Argument @Valid ShopDto shopDto) {
        return services.saveShop(shopDto);
    }

    @MutationMapping
    public ShopResponseDto updateShop(@Argument @Valid ShopDto shopDto) {
        return services.updateShop(shopDto);
    }

    @MutationMapping
    public ShopResponseDto updateShopStorage(@Argument @Valid StorageDto storageDto) {
        return services.updateShopStorage(storageDto);
    }
    @QueryMapping
    public Boolean findShopId(@Argument @Valid UUID id) {
        return services.checkShopById(id);
    }

}
