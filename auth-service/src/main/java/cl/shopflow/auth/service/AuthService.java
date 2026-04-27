package cl.shopflow.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.shopflow.auth.dto.AuthResponse;
import cl.shopflow.auth.dto.LoginRequest;
import cl.shopflow.auth.model.User;
import cl.shopflow.auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    public AuthResponse login(LoginRequest request) {
        log.info("Attempting login for username: {}", request.getUsername());
        User user = userRepository.findByUsername(request.getUsername()).get();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }
        log.info("Successful login for username: {}", request.getUsername());
        String token = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole())
                .expiresIn(jwtExpiration)
                .build();
    }
}
