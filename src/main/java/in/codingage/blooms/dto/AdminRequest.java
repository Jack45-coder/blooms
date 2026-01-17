package in.codingage.blooms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminRequest {
    private String username;
    private String email;
    private String password;

}