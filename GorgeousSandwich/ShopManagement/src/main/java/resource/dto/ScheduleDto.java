package resource.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class ScheduleDto {
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/([0-9]{4}) ([01][0-9]|2[0-3]):[0-5][0-9]$")
    private String openingHours;
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/([0-9]{4}) ([01][0-9]|2[0-3]):[0-5][0-9]$")
    private String closingHours;

    private String dayOfWeek;

}
