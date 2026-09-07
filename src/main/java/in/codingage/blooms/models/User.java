package in.codingage.blooms.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

//@Table(name = "users")
//@Entity

@Getter
@Setter
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private Set<Role> role;
    private String phone;

    @Indexed(unique = true)
    private String email;
    private String userName;
    private String profileUrl;
    private String password;
    private int age;
    private boolean active;
}