package blog.dto;

import blog.database.entity.UserReact;
import lombok.Data;

import java.util.List;

@Data
public class ArticleDTO extends BaseDTO {
    private Long id;

    private String authName;

    private List<String> tags;

    private List<UserReact> userReacts;

    enum React {
        HEART, LIKE
    }

    @Data
    public
    class UserReact {
        private Integer userId;

        private React react;
    }
}
