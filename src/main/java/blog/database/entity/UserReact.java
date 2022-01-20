package blog.database.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Donald Trung
 */
@Entity
@Table(name = "user_react")
@Getter
@Setter
public class UserReact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    private React react;

    public UserReact(User user, Article article, React react) {
        this.user = user;
        this.article = article;
        this.react = react;
    }

    public static React ofReact(String name){
        return React.valueOf(name);
    }

    public static enum React {
        HEART, LIKE;
    }
}
