package cl.shopflow.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.shopflow.auth.dto.UserRequest;
import cl.shopflow.auth.dto.UserResponse;
import cl.shopflow.auth.dto.UserUpdateRequest;
import cl.shopflow.auth.enums.Role;
import cl.shopflow.auth.mapper.UserMapper;
import cl.shopflow.auth.model.User;
import cl.shopflow.auth.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserResponse> getAll() {
        log.info("Fetching all users");
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toResponse).toList();
    }

    public UserResponse create(UserRequest request) {
        log.info("Creating user with username: {}", request.getUsername());
        User user = userMapper.fromRequest(request);
        userRepository.save(user);
        return userMapper.toResponse(user);
    }

    public UserResponse getById(Long id) {
        log.info("Fetching user with id: {}", id);
        User user = userRepository.findById(id).get();
        return userMapper.toResponse(user);
    }

    public UserResponse getByUsername(String username) {
        log.info("Fetching user with username: {}", username);
        User user = userRepository.findByUsername(username).get();
        return userMapper.toResponse(user);
    }

    public UserResponse update(Long id, UserUpdateRequest request) {
        log.info("Updating user with id: {}", id);
        User user = userRepository.findById(id).get();
        if (request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if (request.getFullName() != null) {
            user.setFullName(request.getFullName());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getPhoneNumber() != null) {
            user.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getRole() != null) {
            user.setRole(Role.valueOf(request.getRole()));
        }
        user = userRepository.save(user);
        return userMapper.toResponse(user);
    }

    public void delete(Long id) {
        log.info("Deleting user with id: {}", id);
        userRepository.deleteById(id);
    }
}
