package in.codingage.blooms.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
//@Table(name = "admin")
//@Entity
public class Admin {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private boolean active;

}