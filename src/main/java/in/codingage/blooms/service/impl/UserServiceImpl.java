package in.codingage.blooms.service.impl;

import in.codingage.blooms.dto.LoginRequest;
import in.codingage.blooms.dto.LoginResponse;
import in.codingage.blooms.dto.RegisterRequest;
import in.codingage.blooms.dto.UserResponse;
import in.codingage.blooms.exception.ApplicationException;
import in.codingage.blooms.models.Role;
import in.codingage.blooms.models.User;
import in.codingage.blooms.repository.UserRepository;
import in.codingage.blooms.security.JwtUtils;
import in.codingage.blooms.service.UserService;
import in.codingage.blooms.utlils.RandomIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserResponse mapToResponse(User user){
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUserName(user.getUserName());
        response.setEmail(user.getEmail());
        response.setRole((user.getRole()));
        return response;
    }

    public UserResponse register(@RequestBody RegisterRequest request){
        if(request == null){
            throw new ApplicationException("Request cannot be null");
        }

        userRepository.findByEmail(request.getEmail()).ifPresent(it -> {
            throw new ApplicationException("Email Already Exists!");
        });

        if(request.getUserName() == null || request.getPhone() == null || request.getEmail() == null || request.getPassword() == null){
            throw new ApplicationException("All fields (username, phone, email, password) are required");
        }

        User user = new User();

            user.setRole(Set.of(Role.ROLE_USER));
            user.setUserName(request.getUserName());
            user.setEmail(request.getEmail());
            user.setId(RandomIdUtils.generateRandom(8));
            user.setPhone(request.getPhone());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setAge(request.getAge());
            user.setActive(true);
            user.setProfileUrl("/images/bloomsUserImg.jpg");

        try{
            userRepository.save(user);
        }catch (Exception e){
            throw new RuntimeException("Error while saving user");
        }

        return mapToResponse(user);
    }

    public LoginResponse signin(LoginRequest loginRequest){
        if (loginRequest.getPhone() == null || loginRequest.getPhone().isEmpty() ||
                loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            throw new ApplicationException("Username, Phone & Password required");
        }

        User user = userRepository.findByPhone(loginRequest.getPhone())
                .orElseThrow(() -> new ApplicationException("Invalid Username or Phone"));

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new ApplicationException("Invalid Password!");
        }
        if (!user.isActive()){
            throw new ApplicationException("User account is inactive");
        }

        String token = jwtUtils.generateToken(user.getPhone());

        return new LoginResponse(token, mapToResponse(user));
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