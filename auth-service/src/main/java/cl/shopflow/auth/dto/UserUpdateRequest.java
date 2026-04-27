package cl.shopflow.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateRequest {
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @Size(min = 3, max = 100, message = "Full name must be between 3 and 100 characters")
    private String fullName;

    @Email(message = "Email should be valid")
    private String email;

    @Size(max = 20, message = "Phone number must be less than 20 characters")
    private String phoneNumber;

    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role must be either USER or ADMIN")
    private String role;
}
