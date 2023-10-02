package com.betrybe.agrix.services.exception;

/**
 * Custom exception class for user already exists errors.
 */
public class UserAlreadyExistsException extends BadRequestException {

  /**
   * Constructs a new UserAlreadyExistsException with a default error message.
   */
  public UserAlreadyExistsException() {
    super("Não foi possível cadastrar, nome de usuário já existe!");
  }

}
