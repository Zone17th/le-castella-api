package store.bakery.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import store.bakery.exception.ResourceNotFoundException;
import store.bakery.exception.UnAuthorizedException;

import java.io.FileNotFoundException;

public class GlobalExceptionHandler {
    @ExceptionHandler({FileNotFoundException.class, ResourceNotFoundException.class})
    public ResponseEntity<?> notFoundHandle() {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler({UnAuthorizedException.class})
    public ResponseEntity<?> unAuthor() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
