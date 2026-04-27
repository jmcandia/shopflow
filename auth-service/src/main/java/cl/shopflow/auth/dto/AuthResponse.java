package cl.shopflow.auth.dto;

import cl.shopflow.auth.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String token;
    private String username;
    private Role role;
    private Long expiresIn;
}
