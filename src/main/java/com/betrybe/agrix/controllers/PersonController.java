package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.PersonDto;
import com.betrybe.agrix.controllers.dto.PersonResponseDto;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.services.PersonService;
import com.betrybe.agrix.services.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing Person entities.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Creates a new Person entity.
   *
   * @param personDto The DTO representing the Person entity to be created.
   * @return A ResponseEntity containing the created Person DTO
   *         and HTTP status code 201 (Created).
   * @throws UserAlreadyExistsException If the username already exists.
   */
  @PostMapping()
  public ResponseEntity<PersonResponseDto> createPerson(@RequestBody PersonDto personDto)
      throws UserAlreadyExistsException {
    UserDetails userDetails = personService.loadUserByUsername(personDto.username());
    if (userDetails != null) {
      throw new UserAlreadyExistsException();
    }

    Person newPerson = personService.create(personDto.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(PersonDto.fromEntity(newPerson));
  }

}
