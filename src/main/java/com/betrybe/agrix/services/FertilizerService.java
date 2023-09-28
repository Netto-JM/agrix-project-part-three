package com.betrybe.agrix.services;

import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing Fertilizer entities.
 */
@Service
public class FertilizerService {

  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Inserts a new Fertilizer entity into the database.
   *
   * @param fertilizer The Fertilizer entity to be inserted.
   * @return The inserted Fertilizer entity.
   */
  public Fertilizer insertFertilizer(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  /**
   * Retrieves a fertilizer by its unique identifier.
   *
   * @param id The unique identifier of the fertilizer to retrieve.
   * @return An Optional containing the retrieved Fertilizer entity,
   *         or an empty Optional if no fertilizer with the specified ID is found.
   */
  public Optional<Fertilizer> getFertilizerById(Long id) {
    return fertilizerRepository.findById(id);
  }

  /**
   * Retrieves all Fertilizer entities from the database.
   *
   * @return A List containing all Fertilizer entities.
   */
  public List<Fertilizer> getAllFertilizers() {
    return fertilizerRepository.findAll();
  }
}
