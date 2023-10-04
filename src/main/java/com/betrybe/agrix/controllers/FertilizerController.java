package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.services.FertilizerService;
import com.betrybe.agrix.services.exception.FertilizerNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing Fertilizer entities.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Creates a new Fertilizer entity.
   *
   * @param fertilizerDto The DTO representing the Fertilizer entity to be created.
   * @return A ResponseEntity containing the created Fertilizer DTO
   *         and HTTP status code 201 (Created).
   */
  @PostMapping()
  public ResponseEntity<FertilizerDto> createFertilizer(@RequestBody FertilizerDto fertilizerDto) {
    Fertilizer newFertilizer = fertilizerService.insertFertilizer(fertilizerDto.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(FertilizerDto.fromEntity(newFertilizer));
  }

  /**
   * Retrieves a fertilizer by its unique identifier.
   *
   * @param fertilizerId The unique identifier of the fertilizer to retrieve.
   * @return A FertilizerDto if the fertilizer is found.
   * @throws FertilizerNotFoundException If the fertilizer with the specified ID is not found.
   */
  @GetMapping("/{fertilizerId}")
  public FertilizerDto getFertilizerById(@PathVariable Long fertilizerId)
      throws FertilizerNotFoundException {
    Optional<Fertilizer> optionalFertilizer = fertilizerService.getFertilizerById(fertilizerId);

    return optionalFertilizer.map(FertilizerDto::fromEntity)
        .orElseThrow(FertilizerNotFoundException::new);
  }

  // Using authorization with @PreAuthorize and SpEL (Spring Expression Language) for test purposes.
  /**
   * Retrieves a list of all Fertilizer entities.
   *
   * @param person The authenticated person.
   * @return A list of FertilizerDto objects representing all Fertilizer entities.
   */
  @GetMapping()
  @PreAuthorize("hasAuthority('ADMIN') or #person.username matches 'NettoJM'")
  public List<FertilizerDto> getAllFertilizers(@AuthenticationPrincipal Person person) {
    List<Fertilizer> allFertilizers = fertilizerService.getAllFertilizers();
    return allFertilizers.stream().map(FertilizerDto::fromEntity).toList();
  }
}
