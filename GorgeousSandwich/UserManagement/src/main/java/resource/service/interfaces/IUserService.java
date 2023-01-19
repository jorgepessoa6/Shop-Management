package resource.service.interfaces;

import resource.dto.UserDto;
import resource.dto.response.UserResponseDto;

import java.util.UUID;

public interface IUserService {
    UserResponseDto registerCustomer(UserDto userDto);

    UserResponseDto registerPersonInCharge(UserDto userDto);

    Boolean findPersonInChargeId(UUID personInChargeId);

    Boolean findCustomerId(UUID customerId);
}
