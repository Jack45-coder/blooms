package in.codingage.blooms.service.impl;

import in.codingage.blooms.dto.LoginRequest;
import in.codingage.blooms.dto.UserRequest;
import in.codingage.blooms.dto.UserResponse;
import in.codingage.blooms.models.Role;
import in.codingage.blooms.models.User;
import in.codingage.blooms.repository.UserRepository;
import in.codingage.blooms.service.UserService;
import in.codingage.blooms.utlils.RandomIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private UserResponse mapToResponse(User user){
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }

    public UserResponse register(@RequestBody UserRequest request){
        if(request == null){
            throw new IllegalArgumentException("Request null not required!");
        }

        User user = new User();

        if (request.getName() != null && request.getPhone() != null && request.getEmail() != null && request.getPassword() != null){
            user.setRole(Role.USER);
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setId(RandomIdUtils.generateRandom(8));
            user.setPhone(request.getPhone());
            user.setPassword(request.getPassword());
            user.setActive(true);
            user.setProfileUrl("/images/bloomsUserImg.jpg");
        }
        userRepository.save(user);
        return mapToResponse(user);
    }

    public UserResponse signin(LoginRequest loginRequest){
        if (loginRequest.getPhone() == null || loginRequest.getPhone().isEmpty() ||
                loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Phone & Password required");
        }

        User user = userRepository.findByPhoneAndPassword(loginRequest.getPhone(), loginRequest.getPassword()).orElseThrow(() -> new RuntimeException("Invalid credentials"));
        return mapToResponse(user);
    }


}

