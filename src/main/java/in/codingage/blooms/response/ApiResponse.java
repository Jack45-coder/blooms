package in.codingage.blooms.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse <T>{
    private boolean success;
    private String errorMessage;
    private String successMessage;
    private T data;

    public ApiResponse(){

    }

    public ApiResponse(boolean success, String message, T data){
        this.success = success;
        if(this.success){
            this.successMessage = message;
        }else {
            this.errorMessage = message;
        }
        this.data = data;
    }
}

