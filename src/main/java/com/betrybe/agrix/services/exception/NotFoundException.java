package com.betrybe.agrix.services.exception;

/**
 * Custom exception class for resource not found errors.
 */
public class NotFoundException extends Exception {

  /**
   * Constructs a new NotFoundException with the specified detail message.
   *
   * @param message The detail message. This message is saved for later retrieval
   *                by the getMessage() method.
   */
  public NotFoundException(String message) {
    super(message);
  }
}
