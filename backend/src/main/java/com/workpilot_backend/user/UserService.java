package com.workpilot_backend.user;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.workpilot_backend.auth.JwtService;
import java.time.LocalDateTime;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder ;
    private final JwtService jwtService;
    public UserService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }
    public void createUser(UserDTO request){

        if (!validateEmail(request.getEmail())){
            throw new IllegalArgumentException("Invalid email");
        }
        if (!validatePassword(request.getPassword())){
            throw new IllegalArgumentException("Invalid password");
        }
        if (userRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }
        String hashPassword = encodePassword(request.getPassword());
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(hashPassword);
        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
    }

    public void updateUser(UserDTO request){
        User userToUpdate = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        User currentUser;
    }
    public void deleteUser(){}

    public boolean validateEmail(String email){
        return email.contains("@");
    }

    public boolean validatePassword(String password){
        return password.length()>=8 && password.matches(".*[A-ZÁ-ÚÜÑ].*");
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);

    }
}