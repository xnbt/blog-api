package blog.api.controller;

import blog.api.dto.response.BaseResponse;
import blog.api.service.user.UserService;
import blog.database.entity.User;
import blog.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "api/users")
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse<?>> create(@RequestBody UserDTO userDTO) {
        userService.registerUser(userDTO);
        return ResponseEntity.ok(
                new BaseResponse(AppCodeResponse.SUCCESS.code(), AppCodeResponse.SUCCESS.message(), null)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<?>> login(@RequestBody UserDTO userDTO) {
        User user = userService.login(userDTO);
        BaseResponse response;
        if (null != user) {
            response = new BaseResponse(
                    AppCodeResponse.SUCCESS.code(),
                    AppCodeResponse.SUCCESS.message(),
                    null);
        } else {
            response = new BaseResponse(
                    AppCodeResponse.NOT_FOUND.code(),
                    AppCodeResponse.NOT_FOUND.message(),
                    null);
        }
        return ResponseEntity.ok(response);
    }
}
