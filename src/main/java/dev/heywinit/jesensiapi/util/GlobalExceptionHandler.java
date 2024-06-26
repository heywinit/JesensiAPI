package dev.heywinit.jesensiapi.util;

import dev.heywinit.jesensiapi.exception.ResourceNotFoundException;
import dev.heywinit.jesensiapi.model.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> handleAllExceptions(Exception ex) {
        logger.error("Unexpected error", ex);
        if(ex instanceof ResourceNotFoundException)
            return ResponseEntity.status(500).body(new MessageResponse(ex.getMessage(), false));

        if(ex instanceof NoResourceFoundException)
            return ResponseEntity.status(404).body(new MessageResponse("The resource you're trying to fetch does not exist", false));

        return ResponseEntity.status(500).body(new MessageResponse("Unexpected error", false));
    }
}
