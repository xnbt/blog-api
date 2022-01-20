package blog.dto;

import blog.database.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserReactDTO {
    private Integer userId;

    private Long articleId;

    private React react;


    public enum React {
        HEART, LIKE
    }

}
