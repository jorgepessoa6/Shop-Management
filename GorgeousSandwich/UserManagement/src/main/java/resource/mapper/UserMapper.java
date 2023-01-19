package resource.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import resource.dto.UserDto;
import resource.dto.response.UserResponseDto;
import resource.model.Address;
import resource.model.ROLE;
import resource.model.User;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserMapper implements IUserMapper {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User userDtoToUser(UserDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .address(addressDtoToAddress(userDto.getAddress(), userDto.getPostalCode(), userDto.getLocation()))
                .taxIdNumber(userDto.getTaxIdNumber())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roles(new HashSet<>())
                .build();
    }

    private Address addressDtoToAddress(String address, String postalCode, String location){
        return Address.builder()
                .address(address)
                .postalCode(postalCode)
                .location(location)
                .build();
    }

    @Override
    public UserResponseDto userToUserResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .address(user.getAddress().getAddress())
                .postalCode(user.getAddress().getPostalCode())
                .location(user.getAddress().getLocation())
                .roles(user.getRoles())
                .build();
    }
}
