package com.betrybe.agrix.services.exception;

/**
 * Custom exception class for person not found errors.
 */
public class PersonNotFoundException extends NotFoundException {

  /**
   * Constructs a new PersonNotFoundException with a default error message.
   */
  public PersonNotFoundException() {
    super("Pessoa não encontrada!");
  }

}
