package resource.model;

import lombok.*;
import javax.persistence.Embeddable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class Address {

    private String address;
    private String postalCode;
    private String location;
}
