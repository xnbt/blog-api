package blog.api.service.user;

import blog.database.entity.User;
import blog.dto.UserDTO;

public interface UserService {
    void registerUser(UserDTO userDTO);

    User login(UserDTO userDTO);
}
