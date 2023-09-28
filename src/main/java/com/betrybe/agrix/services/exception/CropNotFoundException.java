package com.betrybe.agrix.services.exception;

/**
 * Custom exception class for crop not found errors.
 */
public class CropNotFoundException extends NotFoundException {

  /**
   * Constructs a new CropNotFoundException with a default error message.
   */
  public CropNotFoundException() {
    super("Plantação não encontrada!");
  }
}
