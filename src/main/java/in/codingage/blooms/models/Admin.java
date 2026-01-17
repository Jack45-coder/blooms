package in.codingage.blooms.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Admin {
    private String id;
    private String username;
    private String email;
    private String password;
    private boolean active;

}