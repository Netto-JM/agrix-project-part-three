package com.betrybe.agrix.services;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import com.betrybe.agrix.services.exception.CropNotFoundException;
import com.betrybe.agrix.services.exception.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing Crop entities.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;

  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Retrieves a crop by its unique identifier.
   *
   * @param id The unique identifier of the crop to retrieve.
   * @return An Optional containing the retrieved Crop entity,
   *         or an empty Optional if no crop with the specified ID is found.
   */
  public Optional<Crop> getCropById(Long id) {
    return cropRepository.findById(id);
  }

  /**
   * Retrieves all Crop entities from the database.
   *
   * @return A List containing all Crop entities.
   */
  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  /**
   * Retrieves a list of Crop entities with harvest dates between specified start and end dates,
   * inclusively.
   *
   * @param start The start date for filtering crops.
   * @param end   The end date for filtering crops.
   * @return A List containing Crop entities with harvest dates between start and end dates,
   *         inclusively.
   */
  public List<Crop> getByHarvestDate(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end);
  }

  /**
   * Associates a fertilizer with a crop.
   *
   * @param cropId       The unique identifier of the crop.
   * @param fertilizerId The unique identifier of the fertilizer.
   * @throws CropNotFoundException       If the crop with the specified ID is not found.
   * @throws FertilizerNotFoundException If the fertilizer with the specified ID is not found.
   */
  @Transactional
  public void addFertilizerToCrop(long cropId, long fertilizerId)
      throws CropNotFoundException, FertilizerNotFoundException {
    Crop crop = cropRepository.findById(cropId)
        .orElseThrow(CropNotFoundException::new);
    Fertilizer fertilizer = fertilizerRepository.findById(fertilizerId)
        .orElseThrow(FertilizerNotFoundException::new);

    crop.getFertilizers().add(fertilizer);

    cropRepository.save(crop);
  }
}
