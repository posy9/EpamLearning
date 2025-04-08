package by.bsu.detailstorage.controller;

import by.bsu.detailstorage.exception.IllegalEntityRemoveException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

import static by.bsu.detailstorage.registry.ErrorMessagesRegistry.SAME_ENTITY_EXISTS;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String MESSAGE_FIELD_NAME = "message";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String field = error.getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });
        return errors;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleEntityNotFound(EntityNotFoundException ex) {
        return Map.of(MESSAGE_FIELD_NAME, ex.getMessage());
    }

    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleEntityExistsException(EntityExistsException ex) {
        return Map.of(MESSAGE_FIELD_NAME, ex.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNoHandlerFound(NoHandlerFoundException ex) {
        return Map.of(MESSAGE_FIELD_NAME, ex.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Map<String, String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return Map.of(MESSAGE_FIELD_NAME, ex.getMessage());
    }

    @ExceptionHandler(IllegalEntityRemoveException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleIllegalEntityRemovingException(IllegalEntityRemoveException ex) {
        return Map.of(MESSAGE_FIELD_NAME, ex.getMessage());
    }
}
