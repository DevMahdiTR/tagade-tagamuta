package tagarde.core.exceptions.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends ExceptionHandler {
    public ResourceNotFoundException(String message) {
        super(message);
    }

}
