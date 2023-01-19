package resource.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import resource.dto.UserDto;
import resource.dto.response.UserResponseDto;
import resource.mapper.IUserMapper;
import resource.model.ROLE;
import resource.model.User;
import resource.repository.IUserRepository;
import resource.service.interfaces.IUserService;
import resource.utils.ExceptionErrorMessage;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService, UserDetailsService {

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try{
            Optional<User> userOpt = Optional.of(userRepository
                    .findUserByEmail(email)
                    .orElseThrow());
            User user = userOpt.get();
            return new org.springframework.security.core.userdetails
                    .User(user.getEmail(), user.getPassword(), true, true, true,true, user.getAuthorities());
        }
        catch (Exception x){
            throw new ExceptionErrorMessage("Login Failed", true);
        }
    }

    @Override
    public UserResponseDto registerCustomer(UserDto userDto) {
        User us = userMapper.userDtoToUser(userDto);
        us.getRoles().add(ROLE.CUSTOMER);
        userRepository.saveAndFlush(us);
        return userMapper.userToUserResponseDto(us);
    }

    @Override
    public UserResponseDto registerPersonInCharge(UserDto userDto) {
        User newUser = userMapper.userDtoToUser(userDto);
        newUser.getRoles().add(ROLE.PERSON_IN_CHARGE);
        return userMapper.userToUserResponseDto(userRepository.save(newUser));
    }

    @Override
    public Boolean findPersonInChargeId(UUID personInChargeId) {
        return userRepository.findUserByIdWithRole(personInChargeId, ROLE.PERSON_IN_CHARGE) != null;
    }

    @Override
    public Boolean findCustomerId(UUID customerId) {
        System.out.println(customerId);
        System.out.println(userRepository.findUserByIdWithRole(customerId, ROLE.CUSTOMER));
        return userRepository.findUserByIdWithRole(customerId, ROLE.CUSTOMER) != null;
    }
}
