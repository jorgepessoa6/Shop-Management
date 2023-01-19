package resource.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import resource.dto.UserDto;
import resource.dto.response.UserResponseDto;
import resource.mapper.IUserMapper;
import resource.model.User;
import resource.repository.IUserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    @Mock
    IUserRepository userRepository;

    @Mock
    IUserMapper userMapper;

    User user = new User();

    UserDto userDto = new UserDto();

    UserResponseDto userResponseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveUser() {
        when(userMapper.userDtoToUser(userDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.userToUserResponseDto(user)).thenReturn(userResponseDto);
        UserService userService = new UserService(userRepository, userMapper);
        assertEquals(userResponseDto,userService.registerPersonInCharge(userDto));
    }

    @Test
    void registerUserInCharge() {
        when(userMapper.userDtoToUser(userDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.userToUserResponseDto(user)).thenReturn(userResponseDto);
        UserService userService = new UserService(userRepository, userMapper);
        assertEquals(userResponseDto,userService.registerPersonInCharge(userDto));
    }

    @Test
    void getUserById() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));
        when(userMapper.userToUserResponseDto(user)).thenReturn(userResponseDto);
        UserService userService = new UserService(userRepository, userMapper);
        assertFalse(userService.findPersonInChargeId(user.getId()));
    }
}
