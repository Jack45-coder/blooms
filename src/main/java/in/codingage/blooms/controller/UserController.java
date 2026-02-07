package in.codingage.blooms.controller;

import in.codingage.blooms.exception.ApplicationException;
import in.codingage.blooms.models.Role;
import in.codingage.blooms.models.User;
import in.codingage.blooms.repository.UserCustomRepository;
import in.codingage.blooms.repository.UserRepository;
import in.codingage.blooms.response.ApiResponse;
import in.codingage.blooms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCustomRepository userCustomRepository;

    @GetMapping("/api/account")
    public User getUserByName(@RequestParam String name){
        return userRepository.findFirstByName(name).orElseThrow(() -> new ApplicationException("User Not Found!"));

    }

    @GetMapping("/api/account/admin")
    public ApiResponse<Page<User>> getAllAdmins(@RequestParam int page, @RequestParam int size){
        log.info("Count of all Admins: {}", userRepository.countByRole(Role.ADMIN));
        log.debug("debug logs");
        log.warn("warn logs");
        log.error("error logs");
        log.trace("trace logs");

        Sort sort = Sort.by("age").descending();

        Sort multiSort = Sort.by(
                Sort.Order.desc("name"),
                Sort.Order.asc("age")
        );

        PageRequest pageRequest = PageRequest.of(page, size).withSort(multiSort);
        Page<User> userPage = userRepository.findAll(pageRequest);
        List<User> users =  userPage.getContent();
        log.info("last page reached or not {}", userPage.isLast());

//        userPage.getTotalPages();
//        userPage.getTotalElements();

        return new ApiResponse<>(true, null, userPage);
    }

    @GetMapping("/api/account/{id}")
    public User getUserById(@PathVariable(value = "id") String name){
        return userRepository.findById(name).orElseThrow(() -> new ApplicationException("User Not Found!"));
    }

    @GetMapping("/api/account/email")
    public ApiResponse<User> findUserByEmail(@RequestParam String email){
        return new ApiResponse<>(true, null, userService.findUserByEmail(email));
    }

    @GetMapping("/api/account/age")
    public List<User> findUsersWithAgeAbove(Integer age){
        return userCustomRepository.findUsersWithAgeAbove(age);
    }

    @GetMapping("/api/account/age-role")
    public List<User> getUsersWithAgeAndRole(@RequestParam Integer age, @RequestParam String role){
        return userCustomRepository.findUsersWithAgeAboveUsingCB(age, role);
    }

    @GetMapping("/api/account/all")
    public List<User> getAllUsers(@RequestParam int page, @RequestParam int size){
        Sort sort = Sort.by("name").ascending();
        PageRequest pageRequest = PageRequest.of(page, size).withSort(sort);
        return userRepository.findAll(pageRequest).getContent();
    }

}
