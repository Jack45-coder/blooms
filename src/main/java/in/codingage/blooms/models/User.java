package in.codingage.blooms.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
//@Table(name = "users")
//@Entity
public class User {
    @Id
    private String id;
    private String username;
    private String email;
    private String name;
    private String profileUrl;
    private String password;
    private boolean active;

}