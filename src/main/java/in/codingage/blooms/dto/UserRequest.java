package in.codingage.blooms.dto;

public class UserRequest {
    private String username;
    private String email;
    private String name;
    private String password;
    private String profileUrl;

    public UserRequest(String username, String email, String name, String password, String profileUrl) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.password = password;
        this.profileUrl = profileUrl;
    }

    public UserRequest(String username, String password){
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}