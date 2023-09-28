package com.betrybe.agrix.services.exception;

/**
 * Custom exception class for fertilizer not found errors.
 */
public class FertilizerNotFoundException extends NotFoundException {

  /**
   * Constructs a new FertilizerNotFoundException with a default error message.
   */
  public FertilizerNotFoundException() {
    super("Fertilizante não encontrado!");
  }

}
