package blog;

import blog.api.service.user.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {
    @Autowired
    private ArticleTagService articleTagService;

    @Override
    public void run(String... args) throws Exception {
        articleTagService.generateTags();
    }
}
