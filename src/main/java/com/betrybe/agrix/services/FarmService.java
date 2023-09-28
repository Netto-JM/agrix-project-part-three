package com.betrybe.agrix.services;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing Farm entities.
 */
@Service
public class FarmService {

  private final FarmRepository farmRepository;

  private final CropRepository cropRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  /**
   * Inserts a new Farm entity into the database.
   *
   * @param farm The Farm entity to be inserted.
   * @return The inserted Farm entity.
   */
  public Farm insertFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Inserts a new crop associated with a farm.
   *
   * @param farmId The ID of the farm to associate the crop with.
   * @param crop   The Crop entity to insert.
   * @return An Optional containing the inserted Crop entity,
   *         or an empty Optional if the farm with farmId is not found.
   */
  public Optional<Crop> insertCrop(Long farmId, Crop crop) {
    Optional<Farm> optionalFarm = farmRepository.findById(farmId);

    if (optionalFarm.isPresent()) {
      Farm farm = optionalFarm.get();
      crop.setFarm(farm);
      Crop newCrop = cropRepository.save(crop);
      return Optional.of(newCrop);
    }

    return Optional.empty();
  }

  /**
   * Retrieves a farm by its unique identifier.
   *
   * @param id The unique identifier of the farm to retrieve.
   * @return An Optional containing the retrieved Farm entity,
   *         or an empty Optional if no farm with the specified ID is found.
   */
  public Optional<Farm> getFarmById(Long id) {
    return farmRepository.findById(id);
  }

  /**
   * Retrieves all Farm entities from the database.
   *
   * @return A List containing all Farm entities.
   */
  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  /**
   * Retrieves a list of Crop entities associated with a specific farm.
   *
   * @param farmId The unique identifier of the farm for which to retrieve crops.
   * @return A List of Crop entities associated with the specified farm,
   *         or an empty List if no crops are found for the given farm.
   */
  public List<Crop> getCropsByFarmId(Long farmId) {
    return cropRepository.findByFarmId(farmId);
  }
}
