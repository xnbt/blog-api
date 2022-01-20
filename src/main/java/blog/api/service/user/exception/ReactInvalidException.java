package blog.api.service.user.exception;

public class ReactInvalidException extends RuntimeException{
    public static final String MESSAGE = "react invalid !";

    public ReactInvalidException(){
        super(MESSAGE);
    }
}
