package resource.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import resource.service.interfaces.IUserService;
import resource.dto.response.UserResponseDto;
import java.util.UUID;
import resource.dto.UserDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@Validated
public class UserController {

    private final IUserService userService;

    @MutationMapping
    public UserResponseDto registerCustomer(@Argument @Valid UserDto userDto) {
        return userService.registerCustomer(userDto);
    }

    @MutationMapping
    public UserResponseDto addPersonInCharge(@Argument @Valid UserDto userDto) {
        return userService.registerPersonInCharge(userDto);
    }

    @QueryMapping
    public Boolean getPersonInChargeId(@Argument @Valid UUID id){
        return userService.findPersonInChargeId(id);
    }

    @QueryMapping
    public Boolean findCustomerId(@Argument @Valid UUID id){
        return userService.findCustomerId(id);
    }
}
