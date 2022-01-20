package blog.api.service.user;

import blog.api.service.user.exception.ArticleNotFoundException;
import blog.api.service.user.exception.ReactInvalidException;
import blog.api.service.user.exception.TagNotFoundException;
import blog.api.service.user.exception.UserNotFoundException;
import blog.database.entity.Article;
import blog.database.entity.Tag;
import blog.database.entity.User;
import blog.database.entity.UserReact;
import blog.database.repository.ArticleRepository;
import blog.database.repository.TagRepository;
import blog.database.repository.UserReactRepository;
import blog.database.repository.UserRepository;
import blog.dto.ArticleDTO;
import blog.dto.BaseDTO;
import blog.dto.PageDTO;
import blog.dto.UserReactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleTagService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserReactRepository userReactRepository;

    @Transactional
    public void generateTags() {
        List<Tag> tags = Arrays.asList(new Tag("Sport"), new Tag("Fashion"), new Tag("Technology"));
        tagRepository.saveAll(tags);

    }

    @Transactional
    public Long createArticle(ArticleDTO articleDTO) {
        Article article = createNewArticle(articleDTO);
        articleRepository.save(article);
        return article.getId();
    }

    @Transactional
    public void userReact(UserReactDTO userReactDTO) {
        Long articleId = userReactDTO.getArticleId();
        Integer requestUserId = userReactDTO.getUserId();
        String reactName = userReactDTO.getReact().name();

        Optional<User> optionalUser = userRepository.findById(requestUserId);

        Optional<Article> optionalUserArticle = articleRepository.findById(articleId);

        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException();
        }

        if (!optionalUserArticle.isPresent()) {
            throw new ArticleNotFoundException();
        }

        UserReact userReact = userReactRepository.findByArticleAndUser(articleId, requestUserId);

        if (null == userReact) {
            createNewUserReact(articleId, requestUserId, reactName);
        }else {
            updateReactOfUser(userReact, reactName);
        }
    }

    private void createNewUserReact(Long articleId, Integer requestUserId, String reactName) {
        UserReact.React reactEntity = UserReact.ofReact(reactName);
        if (null == reactEntity) {
            throw new ReactInvalidException();
        }

        UserReact newUserReact = new UserReact(new User(requestUserId.longValue()), new Article(articleId), reactEntity);
        userReactRepository.save(newUserReact);
    }

    private void updateReactOfUser(UserReact userReact, String reactName) {
        UserReact.React reactEntity = userReact.ofReact(reactName);
        if (null == reactEntity) {
            throw new ReactInvalidException();
        }
        userReact.setReact(reactEntity);

        userReactRepository.save(userReact);
    }

    public PageDTO getAllArticle(Pageable pageable) {
        Page<Article> page = articleRepository.findAll(pageable);

        List<Article> articles = page.getContent();
        long totalElements = page.getTotalElements();
        int totalPages = page.getTotalPages();

        return PageDTO.builder()
                .elements(articles.stream().map(Article::toDTO).collect(Collectors.toList()))
                .totalElements(totalElements)
                .totalPages(totalPages)
                .build();
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    private Article createNewArticle(ArticleDTO articleDTO) {
        String authName = articleDTO.getAuthName();
        List<String> tagNames = articleDTO.getTags();

        List<Tag> tags = tagRepository.findByNameIn(tagNames);

        if (CollectionUtils.isEmpty(tags)) {
            throw new TagNotFoundException();
        }

        Article article = new Article();
        article.setAuthName(authName);
        article.setTags(tags);

        return article;
    }
}
