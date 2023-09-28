package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.FarmService;
import com.betrybe.agrix.services.exception.FarmNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing Farm entities.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;

  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Creates a new Farm entity.
   *
   * @param farmDto The DTO representing the Farm entity to be created.
   * @return A ResponseEntity containing the created Farm DTO and HTTP status code 201 (Created).
   */
  @PostMapping()
  public ResponseEntity<FarmDto> createFarm(@RequestBody FarmDto farmDto) {
    Farm newFarm = farmService.insertFarm(farmDto.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(FarmDto.fromEntity(newFarm));
  }

  /**
   * Creates a new crop associated with a farm.
   *
   * @param farmId   The ID of the farm to associate the crop with.
   * @param cropDto  The CropDto representing the crop to create.
   * @return A ResponseEntity containing the created CropDto.
   * @throws FarmNotFoundException If the specified farm with farmId is not found.
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> createCrop(@PathVariable Long farmId, @RequestBody CropDto cropDto)
      throws FarmNotFoundException {
    Optional<Crop> optionalCrop = farmService.insertCrop(farmId, cropDto.toEntity());

    return optionalCrop.map(newCrop -> {
      CropDto newCropDto = CropDto.fromEntity(newCrop);
      return ResponseEntity.status(HttpStatus.CREATED).body(newCropDto);
    }).orElseThrow(FarmNotFoundException::new);
  }

  /**
   * Retrieves a list of CropDto objects representing crops associated with a specific farm.
   *
   * @param farmId The unique identifier of the farm for which to retrieve crops.
   * @return A List of CropDto objects associated with the specified farm,
   *         or an empty List if no crops are found for the given farm.
   * @throws FarmNotFoundException If the farm with the specified ID is not found.
   */
  @GetMapping("/{farmId}/crops")
  public List<CropDto> getCropsByFarmId(@PathVariable Long farmId)
      throws FarmNotFoundException {
    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);
    if (optionalFarm.isEmpty()) {
      throw new FarmNotFoundException();
    }
    List<Crop> crops = farmService.getCropsByFarmId(farmId);
    return crops.stream().map(CropDto::fromEntity).toList();
  }

  /**
   * Retrieves a farm by its unique identifier.
   *
   * @param farmId The unique identifier of the farm to retrieve.
   * @return ResponseEntity containing a FarmDto if the farm is found.
   * @throws FarmNotFoundException If the farm with the specified ID is not found.
   */
  @GetMapping("/{farmId}")
  public ResponseEntity<FarmDto> getFarmById(@PathVariable Long farmId)
      throws FarmNotFoundException {
    Optional<Farm> optionalFarm = farmService.getFarmById(farmId);

    return optionalFarm.map(farm -> ResponseEntity.ok(FarmDto.fromEntity(farm)))
        .orElseThrow(FarmNotFoundException::new);
  }

  /**
   * Retrieves a list of all Farm entities.
   *
   * @return A list of FarmDto objects representing all Farm entities.
   */
  @GetMapping()
  public List<FarmDto> getAllFarms() {
    List<Farm> allFarms = farmService.getAllFarms();
    return allFarms.stream().map(FarmDto::fromEntity).toList();
  }
}
