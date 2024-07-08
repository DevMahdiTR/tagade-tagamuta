package tagarde.core.exceptions.custom;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedActionException extends ExceptionHandler {
    public UnauthorizedActionException(String message) {
        super(message);
    }
}
