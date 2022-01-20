package blog.api.service.user.exception;

public class ArticleNotFoundException extends RuntimeException{
    public static final String MESSAGE = "article not found !";

    public ArticleNotFoundException(){
        super(MESSAGE);
    }
}
