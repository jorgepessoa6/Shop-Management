package resource.mapper;

import resource.dto.UserDto;
import resource.dto.response.UserResponseDto;
import resource.model.User;

public interface IUserMapper {
    User userDtoToUser(UserDto userDto);

    UserResponseDto userToUserResponseDto(User user);
}
