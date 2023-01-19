package resource.dto.responseDtos;

import lombok.Getter;
import lombok.Setter;
import resource.model.DAYOFTHEWEEK;

@Getter
@Setter
public class ScheduleResponseDto {

    private String openingHours;

    private String closingHours;

    private DAYOFTHEWEEK dayOfWeek;
}
