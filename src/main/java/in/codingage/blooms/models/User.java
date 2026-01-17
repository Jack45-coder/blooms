package in.codingage.blooms.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String username;
    private String email;
    private String name;
    private String profileUrl;
    private String password;
    private boolean active;

}