package blog.api.controller;

import blog.api.dto.response.BaseResponse;
import blog.api.service.user.ArticleTagService;
import blog.dto.ArticleDTO;
import blog.dto.PageDTO;
import blog.dto.UserReactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "api/articles")
public class ArticleController extends BaseController {

    private final ArticleTagService articleTagService;

    @Autowired
    public ArticleController(ArticleTagService articleTagService) {
        this.articleTagService = articleTagService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse<?>> createArticle(@RequestBody ArticleDTO articleDTO) {
        Long id = articleTagService.createArticle(articleDTO);

        return ResponseEntity.ok(
                new BaseResponse(AppCodeResponse.SUCCESS.code(), AppCodeResponse.SUCCESS.message(), id)
        );
    }

    @GetMapping
    public ResponseEntity<BaseResponse<?>> getAllArticle(Pageable pageable) {
        PageDTO page = articleTagService.getAllArticle(pageable);

        return ResponseEntity.ok(
                new BaseResponse(AppCodeResponse.SUCCESS.code(), AppCodeResponse.SUCCESS.message(), page)
        );
    }

    @PostMapping("/user-react")
    public ResponseEntity<BaseResponse<?>> userReact(@RequestBody UserReactDTO userReactDTO) {
        articleTagService.userReact(userReactDTO);

        return ResponseEntity.ok(
                new BaseResponse(AppCodeResponse.SUCCESS.code(), AppCodeResponse.SUCCESS.message(), null)
        );
    }
}
