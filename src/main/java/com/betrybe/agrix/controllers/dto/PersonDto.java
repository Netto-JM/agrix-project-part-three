package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.security.Role;

/**
 * Data Transfer Object (DTO) representing the Person entity.
 */
public record PersonDto(Long id, String username, String password, Role role) {

  /**
   * Creates a PersonDto from a Person entity.
   *
   * @param person The Person entity to convert.
   * @return A PersonDto populated with data from the Person entity.
   */
  public static PersonResponseDto fromEntity(Person person) {
    return new PersonResponseDto(person.getId(), person.getUsername(), person.getRole());
  }

  /**
   * Converts the PersonDto to a Person entity.
   *
   * @return A Person entity with the same attributes as this DTO.
   */
  public Person toEntity() {
    Person person = new Person();
    person.setUsername(username);
    person.setPassword(password);
    person.setRole(role);
    return person;
  }

}
