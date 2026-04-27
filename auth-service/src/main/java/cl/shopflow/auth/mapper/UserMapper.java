package cl.shopflow.auth.mapper;

import org.springframework.stereotype.Component;

import cl.shopflow.auth.dto.UserRequest;
import cl.shopflow.auth.dto.UserResponse;
import cl.shopflow.auth.enums.Role;
import cl.shopflow.auth.model.User;

@Component
public class UserMapper {

    public User fromRequest(UserRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .build();
        // Si el número de teléfono no es nulo, lo asignamos al usuario
        if (request.getPhoneNumber() != null) {
            user.setPhoneNumber(request.getPhoneNumber());
        }
        // Si el rol no es nulo, lo asignamos al usuario, de lo contrario, asignamos el
        // rol USER por defecto
        if (request.getRole() != null) {
            user.setRole(Role.valueOf(request.getRole()));
        } else {
            user.setRole(Role.USER);
        }
        return user;
    }

    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole().name())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
