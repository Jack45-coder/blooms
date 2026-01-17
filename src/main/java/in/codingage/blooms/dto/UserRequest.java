package in.codingage.blooms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}