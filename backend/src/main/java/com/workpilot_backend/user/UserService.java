package com.workpilot_backend.user;
import com.workpilot_backend.exception.UnauthorizedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.workpilot_backend.exception.EmailAlreadyExistsException;
import com.workpilot_backend.exception.UserNotFoundException;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder ;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;
    }
    public void createUser(UserCreateDTO request){

        if (userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists");
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

    public void updateUser(Long id, UserUpdateDTO dto){
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedEmail = authentication.getName();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority ->
                        authority.getAuthority().equals("ROLE_ADMIN"));

        if(!isAdmin && !userToUpdate.getEmail().equals(loggedEmail)){
            throw new UnauthorizedException("You can only update your own user");
        }

        if(userRepository.existsByEmailAndIdNot(dto.getEmail(), id)){
            throw new EmailAlreadyExistsException("Email already exists");
        }
        userToUpdate.setName(dto.getName());
        userToUpdate.setEmail(dto.getEmail());

        if(dto.getPassword() != null && !dto.getPassword().isBlank()){
            userToUpdate.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        }

        userToUpdate.setUpdatedAt(LocalDateTime.now());

        userRepository.save(userToUpdate);
    }

    public void deleteUser(Long id){
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(userToDelete);
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);

    }

    private UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());

        return dto;
    }

    public List<UserResponseDTO> getAllUsers(){
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public UserResponseDTO getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        return toResponseDTO(user);
    }
}