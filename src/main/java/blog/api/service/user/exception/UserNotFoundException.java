package blog.api.service.user.exception;

public class UserNotFoundException extends RuntimeException{
    public static final String MESSAGE = "user not found !";

    public UserNotFoundException(){
        super(MESSAGE);
    }
}
