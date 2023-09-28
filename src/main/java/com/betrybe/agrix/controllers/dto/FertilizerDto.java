package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Fertilizer;

/**
 * Data Transfer Object (DTO) representing the Fertilizer entity.
 */
public record FertilizerDto(Long id, String name, String brand, String composition) {

  /**
   * Creates a FertilizerDto from a Fertilizer entity.
   *
   * @param fertilizer The Fertilizer entity to convert.
   * @return A FertilizerDto populated with data from the Fertilizer entity.
   */
  public static FertilizerDto fromEntity(Fertilizer fertilizer) {
    return new FertilizerDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    );
  }

  /**
   * Converts the FertilizerDto to a Fertilizer entity.
   *
   * @return A Fertilizer entity with the same attributes as this DTO.
   */
  public Fertilizer toEntity() {
    Fertilizer fertilizer = new Fertilizer();
    fertilizer.setName(name);
    fertilizer.setBrand(brand);
    fertilizer.setComposition(composition);
    return fertilizer;
  }
}
