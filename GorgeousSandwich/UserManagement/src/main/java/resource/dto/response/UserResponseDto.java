package resource.dto.response;

import lombok.Builder;
import resource.model.ROLE;

import java.util.Set;
import java.util.UUID;

@Builder
public record UserResponseDto(
                             UUID id,
                             String username,
                             String email,
                             String address,
                             String postalCode,
                             String location,
                             String password,
                             long taxIdNumber,
                             Set<ROLE> roles){
}
