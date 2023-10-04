package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.CropService;
import com.betrybe.agrix.services.exception.CropNotFoundException;
import com.betrybe.agrix.services.exception.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing Crop entities.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Retrieves a list of all Crop entities.
   *
   * @return A list of CropDto objects representing all Crop entities.
   */
  @GetMapping()
  @Secured({"MANAGER", "ADMIN"})
  public List<CropDto> getAllCrops() {
    List<Crop> allCrops = cropService.getAllCrops();
    return allCrops.stream().map(CropDto::fromEntity).toList();
  }

  /**
   * Associates a fertilizer with a crop.
   *
   * @param cropId       The unique identifier of the crop.
   * @param fertilizerId The unique identifier of the fertilizer.
   * @return A ResponseEntity with a success message if the association is successful.
   * @throws CropNotFoundException       If the crop with the specified ID is not found.
   * @throws FertilizerNotFoundException If the fertilizer with the specified ID is not found.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> addFertilizerToCrop(
      @PathVariable Long cropId,
      @PathVariable Long fertilizerId
  ) throws CropNotFoundException, FertilizerNotFoundException {
    cropService.addFertilizerToCrop(cropId, fertilizerId);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * Retrieves a list of all Fertilizer entities associated with a crop.
   *
   * @param cropId The unique identifier of the crop.
   * @return A list of FertilizerDto objects representing all Fertilizer entities associated with
   *         the crop.
   * @throws CropNotFoundException If the crop with the specified ID is not found.
   */
  @GetMapping("/{cropId}/fertilizers")
  public List<FertilizerDto> getFertilizersByCropId(@PathVariable Long cropId)
      throws CropNotFoundException {
    Optional<Crop> optionalCrop = cropService.getCropById(cropId);

    if (optionalCrop.isEmpty()) {
      throw new CropNotFoundException();
    }
    List<Fertilizer> fertilizers = optionalCrop.get().getFertilizers();
    return fertilizers.stream().map(FertilizerDto::fromEntity).toList();
  }

  /**
   * Retrieves a crop by its unique identifier.
   *
   * @param cropId The unique identifier of the crop to retrieve.
   * @return A CropDto if the crop is found.
   * @throws CropNotFoundException If the crop with the specified ID is not found.
   */
  @GetMapping("/{cropId}")
  public CropDto getCropById(@PathVariable Long cropId)
      throws CropNotFoundException {
    Optional<Crop> optionalCrop = cropService.getCropById(cropId);

    return optionalCrop.map(CropDto::fromEntity).orElseThrow(CropNotFoundException::new);
  }

  /**
   * Retrieves a list of all Crop entities that have a harvest date between the specified dates.
   *
   * @param start The start date of the harvest date range.
   * @param end   The end date of the harvest date range.
   * @return A list of CropDto objects representing all Crop entities that have a harvest date
   *         between the specified dates.
   */
  @GetMapping("/search")
  public List<CropDto> getByHarvestDate(
      @RequestParam LocalDate start,
      @RequestParam LocalDate end
  ) {
    List<Crop> allCrops = cropService.getByHarvestDate(start, end);
    return allCrops.stream().map(CropDto::fromEntity).toList();
  }
}
