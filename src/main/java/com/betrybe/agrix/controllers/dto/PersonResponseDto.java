package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.security.Role;

/**
 * Data Transfer Object (DTO) representing the response for a Person entity.
 * This DTO is used to encapsulate the response data when creating or retrieving a Person.
 */
public record PersonResponseDto(Long id, String username, Role role) {

  /**
   * Creates a PersonResponseDto from the attributes of a Person entity.
   *
   * @param id       The ID of the Person.
   * @param username The username of the Person.
   * @param role     The role of the Person.
   * @return A PersonResponseDto populated with the provided attributes.
   */
  public static PersonResponseDto of(Long id, String username, Role role) {
    return new PersonResponseDto(id, username, role);
  }
}