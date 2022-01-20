package blog.database.repository;

import blog.database.entity.UserReact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserReactRepository extends JpaRepository<UserReact, Integer> {
    UserReact findByArticle(String articleId);

    UserReact findByArticleAndUser(Long articleId, Integer userId);

    List<UserReact> findByUserIn(String articleId);
}
