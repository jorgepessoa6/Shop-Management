package resource.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import resource.dto.ScheduleDto;
import resource.dto.ShopDto;
import resource.dto.StorageDto;
import resource.dto.responseDtos.ShopResponseDto;
import resource.mapper.IShopMapper;
import resource.model.Properties;
import resource.model.Schedule;
import resource.model.Shop;
import resource.model.Storage;
import resource.model.external.Sandwich;
import resource.repository.IShopRepository;
import resource.service.interfaces.IShopService;
import resource.utils.ExceptionErrorMessage;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ShopService implements IShopService {

    private final IShopRepository repository;
    private final RestTemplate restTemplate;
    private final IShopMapper shopMapper;

    @Override
    public ShopResponseDto saveShop(ShopDto shopDto) {

        try{
            if(!verifyPIC(shopDto.getPersonInChargeId())){
                throw new ExceptionErrorMessage("The Person In Charge was not found", true);
            }
        }catch (Exception x) {
            throw new ExceptionErrorMessage("The Person In Charge was not found or the service is unavailable", true);
        }

        Shop shop = shopMapper.shopDtoToShop(shopDto);
        Properties p = new Properties(shopDto.getMinimumDelivery(), shopDto.getMaxOrders(), shopDto.getPeriod());
        shop.setProperties(p);
        List<Schedule> scheduleList = new ArrayList<>();

        for (ScheduleDto scheduleDto : shopDto.getSchedules()) {
            scheduleList.add(shopMapper.scheduleDtoToSchedule(scheduleDto));
        }

        shop.setStorages(new ArrayList<>());
        shop.setPersonInChargeId(shopDto.getPersonInChargeId());
        shop.setSchedules(scheduleList);

        return shopMapper.shopToShopResponseDto(repository.save(shop));
    }

    private boolean verifyPIC(UUID id){
        ObjectMapper objectMapper = new ObjectMapper();

        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8090/user").build();
        GraphQLWebClient graphQLWebClient = GraphQLWebClient.newInstance(webClient, objectMapper);
        return Boolean.TRUE.equals(graphQLWebClient.post("getUserPIC.graphql", Map.of("id", id), Boolean.class).block());
    }

    @Override
    public ShopResponseDto updateShop(ShopDto shopDto) {
        Shop shop = repository.findById(shopDto.getId()).orElse(null);
        if (shop==null) return null;
        updateShopHelper(shop, shopDto);

        return shopMapper.shopToShopResponseDto(repository.save(shop));
    }

    private void updateShopHelper(Shop shopToUpdate, ShopDto shopDto) {
        List<Schedule> scheduleList = new ArrayList<>();
        for (ScheduleDto scheduleDto : shopDto.getSchedules()) {
            scheduleList.add(shopMapper.scheduleDtoToSchedule(scheduleDto));
        }
        shopToUpdate.setSchedules(scheduleList);
        shopToUpdate.setAddress(shopDto.getAddress());
        shopToUpdate.setDesignation(shopDto.getDesignation());

    }
    @Override
    public ShopResponseDto updateShopStorage(StorageDto storageDto) {

        Shop shop = repository.findById(storageDto.getShopId()).orElseThrow();

        Storage storage = shopMapper.storageDtoToStorage(storageDto);

        UUID sandwichId = storageDto.getSandwichId();
        try{
            if(findSandwich(storageDto.getSandwichId()) == null){
                return null;
            }
        }catch (Exception x) {
            throw new ExceptionErrorMessage("The Sandwich was not found", false);
        }

        storage.setSandwichId(sandwichId);
        List<Storage> storages = shop.getStorages();

        boolean flag = true;
        for(Storage storage1 : storages){
            if(storage1.getSandwichId() == sandwichId){
                storage1.setQuantity(storage.getQuantity());
                flag = false;
            }
        }
        if(flag) {
            storage.setQuantity(storageDto.getQuantity());
            shop.getStorages().add(storage);
        }
        return shopMapper.shopToShopResponseDto(repository.save(shop));
    }

    private Sandwich findSandwich(UUID sandwichId){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UUID> requestSandwich = new HttpEntity<>(sandwichId,headers);

        return restTemplate.exchange("http://localhost:8095/" + sandwichId,
                HttpMethod.GET, requestSandwich, new ParameterizedTypeReference<Sandwich>() {}).getBody();
    }


    @Override
    public boolean checkShopById(UUID shopId) {
        return repository.existsById(shopId);
    }
}
