package blog.api.service.user;

import blog.database.entity.User;
import blog.database.repository.UserRepository;
import blog.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(UserDTO userDTO) {
        User user = userDTO.toUserEntity();
        userRepository.save(user);
    }

    @Override
    public User login(UserDTO userDTO) {
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();

        return userRepository.findByEmailAndPassword(email, password);
    }
}
