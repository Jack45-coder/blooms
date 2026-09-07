package in.codingage.blooms.service;

import in.codingage.blooms.dto.LoginRequest;
import in.codingage.blooms.dto.LoginResponse;
import in.codingage.blooms.dto.RegisterRequest;
import in.codingage.blooms.dto.UserResponse;
import in.codingage.blooms.models.User;

public interface UserService {

    UserResponse register(RegisterRequest request);

    LoginResponse signin(LoginRequest loginRequest);

    User findUserByEmail(String email);

    User deleteById(String id);

}
