package in.codingage.blooms.controller;

import in.codingage.blooms.models.User;
import in.codingage.blooms.repository.UserCustomRepository;
import in.codingage.blooms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCustomRepository userCustomRepository;

    @GetMapping("/api/account")
    public User getUserByName(@RequestParam String name){
        return userRepository.findByName(name).orElse(null);
    }

    @GetMapping("/api/account/{id}")
    public User getUserById(@PathVariable(value = "id") String name){
        return userRepository.findById(name).orElse(null);
    }

    @GetMapping("/api/account/email")
    public User findUserByEmail(@RequestParam String email){
        return userCustomRepository.findByEmail(email);
    }

    @GetMapping("/api/account/age")
    public List<User> findUsersWithAgeAbove(Integer age){
        return userCustomRepository.findUsersWithAgeAbove(age);
    }

}
