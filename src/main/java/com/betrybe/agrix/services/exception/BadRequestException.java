package com.betrybe.agrix.services.exception;

/**
 * Custom exception class for bad request errors.
 */
public class BadRequestException extends Exception {

  /**
   * Constructs a new BadRequestException with the specified detail message.
   *
   * @param message The detail message. This message is saved for later retrieval
   *                by the getMessage() method.
   */
  public BadRequestException(String message) {
    super(message);
  }
}
