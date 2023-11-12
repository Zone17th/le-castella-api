package store.bakery.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @NotBlank
    @Pattern(regexp = "\\S*", message = "Username must not contain spaces!")
    private String username;

    @NotBlank
    @Pattern(regexp = "\\S*", message = "Password must not contain spaces!")
    private String password;
}
