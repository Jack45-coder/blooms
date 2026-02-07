package in.codingage.blooms.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse <T>{
    private boolean success;
    private String errorMessage;
    private T data;

    public ApiResponse(){

    }

    public ApiResponse(boolean success, String errorMessage, T data){
        this.success = success;
        this.errorMessage = errorMessage;
        this.data = data;
    }
}

