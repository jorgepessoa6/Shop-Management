package resource.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PromotionDto {

    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/([0-9]{4}) ([01][0-9]|2[0-3]):[0-5][0-9]$")
    private String startDate;

    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/([0-9]{4}) ([01][0-9]|2[0-3]):[0-5][0-9]$")
    private String endDate;
    @Min(value = 0L, message = "The discountPercentage must be positive")
    private double discountPercentage;

    private UUID shopId;

    private List<UUID> sandwiches;
}