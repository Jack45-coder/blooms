package in.codingage.blooms.service;

import in.codingage.blooms.dto.LoginRequest;
import in.codingage.blooms.dto.UserRequest;
import in.codingage.blooms.dto.UserResponse;
import in.codingage.blooms.models.User;

public interface UserService {

    UserResponse register(UserRequest request);

    UserResponse signin(LoginRequest loginRequest);

    User findUserByEmail(String email);

    User deleteById(String id);
}
