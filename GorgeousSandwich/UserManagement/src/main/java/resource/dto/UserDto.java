package resource.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@EqualsAndHashCode
@Setter
@ToString
public class UserDto{
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{3,50}$",
            message = "Username must be of 3 to 50 length with no special characters")
    private String username;
    @Email
    private String email;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{3,50}$",
            message = "Address must be of 3 to 50 length with no special characters")
    String address;
    @NotNull
    @Pattern(regexp = "^[0-9]{4}(?:[-\s][0-9]{3})$",
            message = "Invalid Postal Code")
    String postalCode;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{3,50}$",
            message = "Location must be of 3 to 50 length with no special characters")
    String location;

    @NotNull
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$",
            message = "Password must be of 3 to 50 length with no special characters")
    private String password;
    @NotNull
    @Pattern(regexp = "^[0-9]{9}$",
            message = "Tax Id Number must contain 9 digits")
    private String taxIdNumber;
}
