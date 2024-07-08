package tagarde.core.exceptions;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tagarde.core.exceptions.custom.*;
import tagarde.core.exceptions.utility.ApiError;
import tagarde.core.exceptions.utility.ResponseEntityBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

     @ExceptionHandler(FileDeletionException.class)
     protected ResponseEntity<Object> handleFileDeletionException(@NotNull FileDeletionException ex){
         return ex.handle(HttpStatus.BAD_REQUEST,"Failed to delete file.",ex.getMessage());
     }

    @ExceptionHandler(EmailServiceException.class)
    protected  ResponseEntity<Object> handleEmailServiceException(@NotNull EmailServiceException ex){
        return ex.handle(HttpStatus.BAD_REQUEST, "Email Exception.", ex.getMessage());
    }

    @ExceptionHandler(EmailRegisteredException.class)
    protected ResponseEntity<Object> handleEmailRegisteredException(@NotNull EmailRegisteredException ex) {
        return ex.handle(HttpStatus.BAD_REQUEST, "Invalid Email.", ex.getMessage());
    }

    @ExceptionHandler(DatabaseException.class)
    protected ResponseEntity<Object> handleDatabaseException(@NotNull DatabaseException ex) {
        return ex.handle(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex.getMessage());
    }

    @ExceptionHandler(ExpiredTokenException.class)
    protected ResponseEntity<Object> handleExpiredTokenException(@NotNull ExpiredTokenException ex) {
        return ex.handle(HttpStatus.FORBIDDEN, "Token has expired.", ex.getMessage());
    }

    @ExceptionHandler({InvalidTokenException.class, RevokedTokenException.class})
    protected ResponseEntity<Object> handleInvalidTokenException(@NotNull InvalidTokenException ex) {
        return ex.handle(HttpStatus.NOT_FOUND, "Invalid token", ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(@NotNull ResourceNotFoundException ex) {
        return ex.handle(HttpStatus.NOT_FOUND, "Resource not found.", ex.getMessage());
    }

    @ExceptionHandler(UnauthorizedActionException.class)
    protected ResponseEntity<Object> handleUnauthorizedActionException(@NotNull UnauthorizedActionException ex) {
        return ex.handle(HttpStatus.UNAUTHORIZED, "The requested action is unauthorized. Access denied.", ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(@NotNull IllegalArgumentException ex) {
        return handleException(HttpStatus.BAD_REQUEST, "Validation failed", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllException(@NotNull Exception ex, WebRequest request) {
        return handleException(HttpStatus.BAD_REQUEST, "Other exception occurs", ex.getMessage());
    }

    @ExceptionHandler(DisabledException.class)
    protected ResponseEntity<Object> handleDisabledException(@NotNull DisabledException ex) {
        return handleException(HttpStatus.UNAUTHORIZED, "User account disabled. please verify your email!", ex.getMessage());
    }


    //Override Methode should have separate handling exception function , cannot change core code.
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(@NotNull HttpMessageNotReadableException ex, @NotNull HttpHeaders headers, @NotNull HttpStatusCode status, @NotNull WebRequest request) {
        return handleException(HttpStatus.BAD_REQUEST, "Malformed JSON found.", ex.getMessage());
    }


    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(@NotNull HttpMediaTypeNotSupportedException ex,  @NotNull HttpHeaders headers, @NotNull HttpStatusCode status, @NotNull WebRequest request) {
        return handleException(HttpStatus.BAD_REQUEST, "Unsupported Media Type", ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(@NotNull NoHandlerFoundException ex, @NotNull HttpHeaders headers, @NotNull HttpStatusCode status, @NotNull WebRequest request) {
        return handleException(HttpStatus.NOT_FOUND, "Method not supported", ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NotNull MethodArgumentNotValidException ex,  @NotNull HttpHeaders headers, @NotNull HttpStatusCode status, @NotNull WebRequest request) {
        return handleException(
                HttpStatus.BAD_REQUEST,
                "Validation Errors",
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(error -> error.getField() + " : " + error.getDefaultMessage())
                        .collect(Collectors.joining("; ")));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(@NotNull HttpRequestMethodNotSupportedException ex,  @NotNull HttpHeaders headers, @NotNull HttpStatusCode status, @NotNull WebRequest request) {
        return handleException(HttpStatus.NOT_FOUND, "Method not supported", ex.getMessage());
    }


    private @NotNull ResponseEntity<Object> handleException(HttpStatus status, String message, String errorMessage) {
        List<String> details = new ArrayList<>();
        details.add(errorMessage);

        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .message(message)
                .errors(details)
                .build();
        return ResponseEntityBuilder.build(apiError);
    }
}