package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Farm;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object (DTO) representing the Farm entity.
 */
public record FarmDto(Long id, String name, Double size) {

  /**
   * Creates a FarmDto from a Farm entity.
   *
   * @param farm The Farm entity to convert.
   * @return A FarmDto populated with data from the Farm entity.
   */
  public static FarmDto fromEntity(Farm farm) {
    return new FarmDto(farm.getId(), farm.getName(), farm.getSize());
  }

  /**
   * Converts the FarmDto to a Farm entity.
   *
   * @return A Farm entity with the same attributes as this DTO.
   */
  public Farm toEntity() {
    Farm farm = new Farm();
    farm.setName(name);
    farm.setSize(size);
    return farm;
  }
}
