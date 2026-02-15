package in.codingage.blooms.service.impl;

import in.codingage.blooms.dto.LoginRequest;
import in.codingage.blooms.dto.UserRequest;
import in.codingage.blooms.dto.UserResponse;
import in.codingage.blooms.exception.ApplicationException;
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
        response.setRole(user.getRole());
        return response;
    }

    public UserResponse  register(@RequestBody UserRequest request){
        if(request == null){
            throw new ApplicationException("Request null not required!");
        }

        userRepository.findByEmail(request.getEmail()).ifPresent(it -> {
            throw new ApplicationException("Email Already Exists!");
        });

        User user = new User();

        if (request.getName() != null && request.getPhone() != null && request.getEmail() != null && request.getPassword() != null){
            user.setRole(Role.USER);
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setId(RandomIdUtils.generateRandom(8));
            user.setPhone(request.getPhone());
            user.setPassword(request.getPassword());
            user.setAge(request.getAge());
            user.setActive(true);
            user.setProfileUrl("/images/bloomsUserImg.jpg");
        }
        try{
            userRepository.save(user);
        }catch (Exception e){
            throw new RuntimeException("Error while saving user");
        }

        return mapToResponse(user);
    }

    public UserResponse signin(LoginRequest loginRequest){
        if (loginRequest.getPhone() == null || loginRequest.getPhone().isEmpty() ||
                loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            throw new ApplicationException("Phone & Password required");
        }

        User user = userRepository.findByPhone(loginRequest.getPhone())
                .orElseThrow(() -> new ApplicationException("Invalid Phone No!"));

        if(!user.getPassword().equals(loginRequest.getPassword())){
            throw new ApplicationException("Invalid Password!");
        }
        return mapToResponse(user);
    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ApplicationException("User not found with email: " + email));
    }

    public User deleteById(String id){
        User user = userRepository.findById(id).orElseThrow(() -> new ApplicationException("User not Found!"));
        if (user.isActive()){
            user.setActive(false);
            userRepository.save(user);
        }
        return user;
    }
}