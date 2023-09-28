package com.betrybe.agrix.solution;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.services.exception.PersonNotFoundException;
import com.betrybe.agrix.models.repositories.PersonRepository;
import com.betrybe.agrix.services.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@DisplayName("Tests for PersonService")
@TestInstance(Lifecycle.PER_CLASS)
public class PersonServiceTest {

  @Autowired
  private PersonService personService;

  @Autowired
  private PersonRepository personRepository;

  @BeforeAll
  public void setupDatabase() {
    Person myUser = new Person();
    myUser.setUsername("NettoJM");
    myUser.setPassword("mySecretPassword");

    personRepository.save(myUser);
  }

  @Test
  @DisplayName("Should create a new person")
  public void testNewPerson() {
    Person newPerson = new Person();
    newPerson.setUsername("aNewUser");
    newPerson.setPassword("aNewPassword");

    Person expectedPerson = personService.create(newPerson);

    Assertions.assertNotNull(expectedPerson);

    Assertions.assertEquals(newPerson.getUsername(), expectedPerson.getUsername());
    Assertions.assertEquals(newPerson.getPassword(), expectedPerson.getPassword());
    Assertions.assertEquals(newPerson.getId(), expectedPerson.getId());
  }

  @Test
  @DisplayName("Should find a user by its ID")
  public void testFindUserById() throws PersonNotFoundException {
    Person myUser = personService.getPersonById(1L);

    Assertions.assertEquals(myUser.getId(), 1L);
    Assertions.assertEquals(myUser.getUsername(), "NettoJM");
    Assertions.assertEquals(myUser.getPassword(), "mySecretPassword");
  }

  @Test
  @DisplayName("Should find a user by its username")
  public void testFindUserByUsername() throws PersonNotFoundException {
    Person myUser = personService.getPersonByUsername("NettoJM");

    Assertions.assertEquals(myUser.getId(), 1L);
    Assertions.assertEquals(myUser.getUsername(), "NettoJM");
    Assertions.assertEquals(myUser.getPassword(), "mySecretPassword");
  }

  @Test
  @DisplayName("Should throw an exception when trying to find a non-existent user")
  public void testInvalidUser() {
    Assertions.assertThrows(PersonNotFoundException.class,
        () -> personService.getPersonByUsername("notFoundUser"));
  }
}
