package resource.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String openingHours;

    private String closingHours;

    @Enumerated(EnumType.STRING)
    private DAYOFTHEWEEK dayOfTheWeek;
}
