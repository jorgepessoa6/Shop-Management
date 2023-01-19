package resource.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class ShopDto {

    private UUID id;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{3,50}$",
            message = "Address must be of 3 to 50 length with no special characters")
    private String address;
    private UUID personInChargeId;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{3,50}$",
            message = "Designation must be of 3 to 50 length with no special characters")
    private String designation;
    private List<ScheduleDto> schedules;
    @Min(value = 0L, message = "The minimumDelivery must be positive")
    private int minimumDelivery;
    @Min(value = 0L, message = "The maxOrders must be positive")
    private int maxOrders;
    @Min(value = 0L, message = "The period must be positive")
    private int period;

}
