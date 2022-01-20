package blog.api.service.user.exception;

public class TagNotFoundException extends RuntimeException{
    public static final String MESSAGE = "tag not found !";

    public TagNotFoundException(){
        super(MESSAGE);
    }
}
