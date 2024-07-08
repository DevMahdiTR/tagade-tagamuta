package tagarde.core.exceptions.custom;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailServiceException  extends  ExceptionHandler{

    public EmailServiceException(String message) {
        super(message);
    }

}
