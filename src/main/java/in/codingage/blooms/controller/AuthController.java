package in.codingage.blooms.controller;

import in.codingage.blooms.dto.LoginRequest;
import in.codingage.blooms.dto.LoginResponse;
import in.codingage.blooms.dto.RegisterRequest;
import in.codingage.blooms.dto.UserResponse;
import in.codingage.blooms.exception.ApplicationException;
import in.codingage.blooms.models.User;
import in.codingage.blooms.response.ApiResponse;
import in.codingage.blooms.security.JwtUtils;
import in.codingage.blooms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

// Aapka React local URL
@CrossOrigin(origins = {"http://localhost:5173", "https://blooms-ui.onrender.com"})
@RestController
@RequestMapping("/api/account")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ApiResponse<UserResponse> register(@RequestBody RegisterRequest request){
        UserResponse user = userService.register(request);
        return new ApiResponse<>(true, "Register successfully", user);
    }

//    @PostMapping("/login")
//    public ApiResponse<LoginResponse> signin(@RequestBody LoginRequest loginRequest){
////        try {
////            LoginResponse user = userService.signin(loginRequest);
////            return new ApiResponse<>(true, null, user);
////        }catch (ApplicationException e) {
////            return new ApiResponse<>(false, e.getMessage(), null);
////        }
//    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> signin(@RequestBody LoginRequest loginRequest){

        // Step 1: Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getPhone(),
                        loginRequest.getPassword()
                )
        );

//        String jwt = jwtUtils.generateToken(loginRequest.getUserName());
        LoginResponse user = userService.signin(loginRequest);

        return new ApiResponse<>(true, "Login successfully", user);
    }

    @PostMapping("/logout")
    public String logout(){
        // Invalidate JWT on client side
        // TODO - invalid token - store the invalid token

        return "Logout successful";
    }


}
