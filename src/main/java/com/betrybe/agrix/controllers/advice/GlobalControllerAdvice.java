package com.betrybe.agrix.controllers.advice;

import com.betrybe.agrix.services.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global controller advice to handle exceptions globally across all controllers.
 */
@ControllerAdvice
public class GlobalControllerAdvice {

  /**
   * Exception handler for NotFoundException.
   *
   * @param exception The NotFoundException to handle.
   * @return A ResponseEntity with a 404 status code and the exception message.
   */
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<String> handleNotFoundException(NotFoundException exception) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(
            exception.getMessage()
        );
  }
}
