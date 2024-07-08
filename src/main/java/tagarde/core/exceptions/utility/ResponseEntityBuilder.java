package tagarde.core.exceptions.utility;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {

    public static @NotNull ResponseEntity<Object> build(@NotNull final ApiError apiError){
        return new ResponseEntity<>(apiError, HttpStatusCode.valueOf(apiError.getStatus()));
    }

}
