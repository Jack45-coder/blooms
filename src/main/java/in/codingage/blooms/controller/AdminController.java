package in.codingage.blooms.controller;

import in.codingage.blooms.Database;
import in.codingage.blooms.dto.AdminRequest;
import in.codingage.blooms.dto.AdminResponse;
import in.codingage.blooms.models.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminController {

    // Signup - Admin registration
    public String signup(AdminRequest request){
        if(request == null) return "Request cannot be null!";
        if(request.getUsername() == null || request.getUsername().isEmpty()) return "Username is required!";
        if(request.getEmail() == null || request.getEmail().isEmpty()) return "Email is required!";
        if (request.getPassword() == null || request.getPassword().isEmpty()) return "Password is required!";

        List<Admin> adminList = Database.getInstance().getAdminList();
        for (Admin existAdmin : adminList){
            if(request.getUsername().equals(existAdmin.getUsername())){
                return "Username already exists!";
            }
            if (request.getEmail().equals(existAdmin.getEmail())){
                return "Email already exits!";
            }
        }
        // create admin
        Admin admin = new Admin();
        admin.setUsername(request.getUsername());
        admin.setEmail(request.getEmail());
        admin.setPassword(request.getPassword());
        admin.setActive(true);
        adminList.add(admin);

        return "Admin Registered successfully.";
    }

    // signin - Login user
    public AdminResponse signinAdmin(AdminRequest request){
        if(request == null) {
            System.out.println("Request cannot be null!");
            return null;
        }
        if ((request.getUsername() == null || request.getUsername().isEmpty()) && (request.getEmail() == null || request.getEmail().isEmpty())){
            System.out.println("Username or Email required!");
            return null;
        }
        if(request.getPassword() == null || request.getPassword().isEmpty()){
            System.out.println("Password is required!");
            return null;
        }

        List<Admin> adminList = Database.getInstance().getAdminList();
        for (Admin admin : adminList) {
            boolean usernameMatch = request.getUsername() != null && request.getUsername().equals(admin.getUsername());
            boolean emailMatch = request.getEmail() != null && request.getEmail().equals(admin.getEmail());
            boolean passwordMatch = request.getPassword().equals(admin.getPassword());

            if ((usernameMatch || emailMatch) && passwordMatch) {
                AdminResponse adminResponse = new AdminResponse();
                adminResponse.setUsername(admin.getUsername());
                adminResponse.setEmail(admin.getEmail());
                System.out.println("Admin login successfully");
                return adminResponse;
            }
        }
        System.out.println("Invalid credentials");
        return null;
    }

    public AdminResponse getAdmin(String usernameOrEmail){
        if(usernameOrEmail == null || usernameOrEmail.isEmpty()){
            System.out.println("Username or Email required!");
            return null;
        }

        List<Admin> adminList = Database.getInstance().getAdminList();
        for(Admin admin : adminList){
            if (usernameOrEmail.equals(admin.getUsername()) || usernameOrEmail.equals(admin.getEmail())){
                if (!admin.isActive()) continue;
                AdminResponse adminResponse = new AdminResponse();
                adminResponse.setUsername(admin.getUsername());
                adminResponse.setEmail(admin.getEmail());

                return adminResponse;
            }
        }

        System.out.println("Admin not found!");
        return null;
    }

    public List<AdminResponse> getAllAdmins(){
        List<AdminResponse> adminResponses = new ArrayList<>();
        List<Admin> admins = Database.getInstance().getAdminList();

        for (Admin admin : admins){
            if (!admin.isActive()) continue;

            AdminResponse adminResponse = new AdminResponse();
            adminResponse.setUsername(admin.getUsername());
            adminResponse.setEmail(admin.getEmail());

            adminResponses.add(adminResponse);
        }
        return adminResponses;
    }

    public AdminResponse updateAdmin(AdminRequest request){
        if (request == null) {
            System.out.println("Request cannot be null!");
            return null;
        }

        List<Admin> admins = Database.getInstance().getAdminList();
        for (Admin admin : admins){
            if (request.getUsername() != null && request.getUsername().equals(admin.getUsername()) || (request.getEmail() != null && request.getEmail().equals(admin.getEmail()))){
                if (request.getUsername() != null) admin.setUsername(request.getUsername());
                if (request.getEmail() != null) admin.setEmail(request.getEmail());
                if (request.getPassword() != null) admin.setPassword(request.getPassword());

                AdminResponse adminResponse = new AdminResponse();
                adminResponse.setUsername(admin.getUsername());
                adminResponse.setEmail(admin.getEmail());
                adminResponse.setPassword(admin.getPassword());
                return adminResponse;
            }
        }
        System.out.println("Admin not found!");
        return null;
    }

    public boolean deleteAdmin(String usernameOrEmail){
        if (usernameOrEmail == null || usernameOrEmail.isEmpty()){
            System.out.println("Username Or Email cannot be null!");
            return false;
        }
        List<Admin> admins = Database.getInstance().getAdminList();
        for (Admin admin : admins){
            if (!admin.isActive()) continue;
            if (usernameOrEmail.equals(admin.getUsername()) || usernameOrEmail.equals(admin.getEmail())){
                System.out.println("Admin deleted successfully.");
                admin.setActive(false);
                return true;
            }
        }
        System.out.println("Admin not found");
        return false;
    }
}
