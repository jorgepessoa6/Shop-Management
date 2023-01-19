package resource.dto.responseDtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ShopResponseDto {

    private String address;

    private String designation;

    private List<ScheduleResponseDto> schedules;

    private List<StorageResponseDto> storages;

    private UUID personInChargeId;
}
