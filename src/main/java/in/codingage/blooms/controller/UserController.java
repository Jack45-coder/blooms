package in.codingage.blooms.controller;

import in.codingage.blooms.Database;
import in.codingage.blooms.dto.UserRequest;
import in.codingage.blooms.dto.UserResponse;
import in.codingage.blooms.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    // signup - create user
    public String signup(UserRequest userRequest){

        // basic validation
        if(userRequest == null) return "Request cannot be null!";
        if(userRequest.getUsername() == null || userRequest.getUsername().isEmpty()) return "Username is required!";
        if(userRequest.getEmail() == null || userRequest.getEmail().isEmpty()) return "Email is required";
        if(userRequest.getPassword() == null || userRequest.getPassword().isEmpty()) return "Password is required";

        // duplicate check
        for(User existUser : Database.getInstance().getUserList()){
            if(userRequest.getUsername().equals(existUser.getUsername())){
                return "Username already exists!";
            }
            if (userRequest.getEmail().equals(existUser.getEmail())){
                return "Email already exists!";
            }
        }

        // create user
        User user = new User();
        user.setName(userRequest.getName());
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setProfileUrl(userRequest.getProfileUrl());

        Database.getInstance().getUserList().add(user);
        return "Registered successfully!";
    }

    // signin - fetch user
    public UserResponse signin(UserRequest userRequest) {
        if (userRequest == null) return null;

        // basic validation
        if ((userRequest.getUsername() == null || userRequest.getUsername().isEmpty()) && (userRequest.getEmail() == null || userRequest.getEmail().isEmpty())) {
            System.out.println("Username or Email required!");
            return null;
        }

        if (userRequest.getPassword() == null || userRequest.getPassword().isEmpty()) {
            System.out.println("Password required!");
            return null;
        }

        // check user in db
        for (User user : Database.getInstance().getUserList()) {
            boolean usernameMatch = userRequest.getUsername() != null && userRequest.getUsername().equals(user.getUsername());
            boolean userEmailMatch = userRequest.getEmail() != null && userRequest.getEmail().equals(user.getEmail());
            boolean passwordMatch = userRequest.getPassword().equals(user.getPassword());

            if((usernameMatch || userEmailMatch) && passwordMatch){
                UserResponse userResponse = new UserResponse();
                userResponse.setUsername(user.getUsername());
                userResponse.setEmail(user.getEmail());
                userResponse.setName(user.getName());
                userResponse.setProfileUrl(user.getProfileUrl());
                System.out.println("Login successfully");
                return userResponse;
            }
        }
        System.out.println("Invalid credentials!");
        return null;
    }

    // update user details
    public UserResponse updateUser(UserRequest userRequest){
        List<User> userList = Database.getInstance().getUserList();
        if(userRequest == null) {
            System.out.println("Request cannot be null!");
            return null;
        }

        for(User user : userList){
            if((userRequest.getUsername() != null && userRequest.getUsername().equals(user.getUsername())) || userRequest.getEmail() != null && userRequest.getEmail().equals(user.getEmail())){
                // update fields (only if provided)
                if(userRequest.getName() != null) user.setName(userRequest.getName());
                if(userRequest.getEmail() != null) user.setEmail(userRequest.getEmail());
                if(userRequest.getProfileUrl() != null) user.setProfileUrl(userRequest.getProfileUrl());
                if (userRequest.getPassword() != null) user.setPassword(userRequest.getPassword());

                UserResponse response = new UserResponse();
                response.setUsername(user.getUsername());
                response.setEmail(user.getEmail());
                response.setName(user.getName());
                response.setProfileUrl(user.getProfileUrl());
                return response;
            }
        }
        System.out.println("User not found");
        return null;
    }

    // Delete user

    public boolean deleteUser(String usernameOrEmail){
        if(usernameOrEmail == null || usernameOrEmail.isEmpty()) {
            System.out.println("Username or Email required!");
            return false;
        }

        List<User> userList = Database.getInstance().getUserList();

        for (User user : userList){

            if (!user.isActive()) {
                continue; // already deleted user skip
            }

            if(usernameOrEmail.equals(user.getUsername()) || usernameOrEmail.equals(user.getEmail())){
                user.setActive(false);
                System.out.println("User deleted successfully.");
                return true;
            }
        }
        System.out.println("User not found!");
        return false;
    }

    // crud...

    // fetch User
    public UserResponse getUser(String usernameOrEmail){
        if(usernameOrEmail == null || usernameOrEmail.isEmpty()){
            System.out.println("Username or Email required!");
            return null;
        }

        List<User> userList = Database.getInstance().getUserList();
        for (User user : userList){
            if (user.isActive()) continue;
            if(usernameOrEmail.equals(user.getUsername()) || usernameOrEmail.equals(user.getEmail())){
               UserResponse response = new UserResponse();
               response.setUsername(user.getUsername());
               response.setEmail(user.getEmail());
               response.setName(user.getName());
               response.setProfileUrl(user.getProfileUrl());

               return response;
            }
        }

        System.out.println("User not found!");
        return null;
    }

    // Fetch all users:
    public  List<UserResponse> getAllUser(){

        List<UserResponse> responseList = new ArrayList<>();

        List<User> userList = Database.getInstance().getUserList();
        for(User user : userList){
            if (!user.isActive()) continue;
            UserResponse userResponse = new UserResponse();
            userResponse.setUsername(user.getUsername());
            userResponse.setEmail(user.getEmail());
            userResponse.setName(user.getName());
            userResponse.setProfileUrl(user.getProfileUrl());

            responseList.add(userResponse);
        }

        return responseList;
    }


}
