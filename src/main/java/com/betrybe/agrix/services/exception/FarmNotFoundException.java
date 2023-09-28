package com.betrybe.agrix.services.exception;

/**
 * Custom exception class for farm not found errors.
 */
public class FarmNotFoundException extends NotFoundException {

  /**
   * Constructs a new FarmNotFoundException with a default error message.
   */
  public FarmNotFoundException() {
    super("Fazenda não encontrada!");
  }
}
