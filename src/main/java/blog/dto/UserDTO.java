package blog.dto;

import blog.database.entity.User;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String userName;
    private String email;
    private String password;

    public User toUserEntity() {
        User user = new User();
        if (null != id) {
            user.setId(id);
        }
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }
}
