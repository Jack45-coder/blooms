package in.codingage.blooms.controller;

import in.codingage.blooms.dto.LoginRequest;
import in.codingage.blooms.dto.UserRequest;
import in.codingage.blooms.dto.UserResponse;
import in.codingage.blooms.exception.ApplicationException;
import in.codingage.blooms.response.ApiResponse;
import in.codingage.blooms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Aapka React local URL
@CrossOrigin(origins = "https://blooms-ui.onrender.com")
@RestController
@RequestMapping("/api/account")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ApiResponse<UserResponse> register(@RequestBody UserRequest request){
        UserResponse user = userService.register(request);
        return new ApiResponse<>(true, "Register successfully", user);
    }

    @PostMapping("/login")
    public ApiResponse<UserResponse> signin(@RequestBody LoginRequest loginRequest){
        try {
            UserResponse user = userService.signin(loginRequest);
            return new ApiResponse<>(true, null, user);
        }catch (ApplicationException e) {
            return new ApiResponse<>(false, e.getMessage(), null);
        }
    }

}
