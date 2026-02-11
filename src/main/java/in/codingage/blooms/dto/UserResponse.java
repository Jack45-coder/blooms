<<<<<<< Updated upstream
package in.codingage.blooms.dto;

public class UserResponse {
    private String username;
    private String email;
    private String name;
    private String profileUrl;

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

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", profileUrl='" + profileUrl + '\'' +
                '}';
    }
=======
package in.codingage.blooms.dto;

import in.codingage.blooms.models.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String id;
    private String name;
    private String email;
    private Role role;
>>>>>>> Stashed changes
}