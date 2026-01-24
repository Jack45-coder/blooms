package in.codingage.blooms.controller;

import in.codingage.blooms.dto.LoginRequest;
import in.codingage.blooms.dto.UserRequest;
import in.codingage.blooms.dto.UserResponse;
import in.codingage.blooms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public UserResponse register(@RequestBody UserRequest request){
        return userService.register(request);
    }

    @PostMapping("/login")
    public UserResponse signin(@RequestBody LoginRequest loginRequest){
        return userService.signin(loginRequest);
    }
}
