package store.bakery.model.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String fullName;
    private String username;
    private String accessToken;
}
