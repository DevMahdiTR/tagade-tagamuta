package tagarde.core.utility;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CustomerResponse<T> extends ResponseEntity<T> {

    private final String message = "success";
    private final LocalDateTime timestamp = LocalDateTime.now();
    private HttpStatusCode status;
    private T data;

    public CustomerResponse(T body,HttpStatusCode status) {
        super( (T) createBody(status,body),status);
        this.status = status;
        this.data = body;
    }

    public CustomerResponse( T body ,HttpHeaders headers,HttpStatusCode status) {
        super((T)createBody(status, body), headers, status);
        this.status = status;
        this.data = body;
    }

    private static  Object createBody(HttpStatusCode status, Object body) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", status.value());
        responseBody.put("message", "success");
        responseBody.put("timestamp", LocalDateTime.now());
        responseBody.put("data", body);
        return responseBody;
    }

}
