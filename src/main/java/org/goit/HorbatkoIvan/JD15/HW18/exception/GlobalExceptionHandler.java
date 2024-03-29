package org.goit.HorbatkoIvan.JD15.HW18.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@SuppressWarnings("checkstyle:MissingJavadocType")
@ControllerAdvice
public class GlobalExceptionHandler {

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  @ExceptionHandler(NoteNotFoundException.class)
  public ResponseEntity<?> resourceNotFoundException(NoteNotFoundException ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails("Note Not Found", ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
  public ResponseEntity<?> handleConstraintViolationException(jakarta.validation.ConstraintViolationException ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails("Validation Error", ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails("Error occurred", ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
