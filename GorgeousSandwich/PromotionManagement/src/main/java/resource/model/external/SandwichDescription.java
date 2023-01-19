package resource.model.external;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SandwichDescription {

    private UUID id;
    private String description;
    private String language;
}
