package com.betrybe.agrix.models.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

/**
 * Represents a crop entity.
 */
@Entity
@Table(name = "crop")
public class Crop {

  /**
   * The unique identifier of the crop.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * The name of the crop.
   */
  private String name;

  /**
   * The planted area of the crop in acres.
   */
  @Column(name = "planted_area")
  private Double plantedArea;

  /**
   * The date when the crop was planted.
   */
  @Column(name = "planted_date")
  private LocalDate plantedDate;

  /**
   * The date when the crop was or is expected to be harvested.
   */
  @Column(name = "harvest_date")
  private LocalDate harvestDate;


  /**
   * The farm to which this crop belongs.
   */
  @ManyToOne
  @JoinColumn(name = "farm_id")
  private Farm farm;

  /**
   * The fertilizers used in this crop.
   */
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  private List<Fertilizer> fertilizers;

  // Construtores, getters e setters

  // Construtor vazio
  public Crop() {}

  // Construtor parametrizado
  /**
   * Parameterized constructor for Crop.
   *
   * @param id          The unique identifier of the crop.
   * @param name        The name of the crop.
   * @param plantedArea The planted area of the crop in acres.
   * @param plantedDate The date when the crop was planted.
   * @param harvestDate The date when the crop was or is expected to be harvested.
   * @param farm        The farm to which this crop belongs.
   * @param fertilizers The fertilizers used in this crop.
   */
  public Crop(
      Long id,
      String name,
      Double plantedArea,
      LocalDate plantedDate,
      LocalDate harvestDate,
      Farm farm,
      List<Fertilizer> fertilizers
  ) {
    this.id = id;
    this.name = name;
    this.plantedArea = plantedArea;
    this.plantedDate = plantedDate;
    this.harvestDate = harvestDate;
    this.farm = farm;
    this.fertilizers = fertilizers;
  }

  /**
   * Get the unique identifier of the crop.
   *
   * @return The unique identifier of the crop.
   */
  public Long getId() {
    return id;
  }

  /**
   * Set the unique identifier of the crop.
   *
   * @param id The unique identifier of the crop.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Get the name of the crop.
   *
   * @return The name of the crop.
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of the crop.
   *
   * @param name The name of the crop.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the planted area of the crop in acres.
   *
   * @return The planted area of the crop.
   */
  public Double getPlantedArea() {
    return plantedArea;
  }

  /**
   * Set the planted area of the crop in acres.
   *
   * @param plantedArea The planted area of the crop.
   */
  public void setPlantedArea(Double plantedArea) {
    this.plantedArea = plantedArea;
  }

  /**
   * Get the date when the crop was planted.
   *
   * @return The date when the crop was planted.
   */
  public LocalDate getPlantedDate() {
    return plantedDate;
  }

  /**
   * Set the date when the crop was planted.
   *
   * @param plantedDate The date when the crop was planted.
   */
  public void setPlantedDate(LocalDate plantedDate) {
    this.plantedDate = plantedDate;
  }

  /**
   * Get the date when the crop was or is expected to be harvested.
   *
   * @return The date when the crop was or is expected to be harvested.
   */
  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  /**
   * Set the date when the crop was or is expected to be harvested.
   *
   * @param harvestDate The date when the crop was or is expected to be harvested.
   */
  public void setHarvestDate(LocalDate harvestDate) {
    this.harvestDate = harvestDate;
  }

  /**
   * Get the farm to which this crop belongs.
   *
   * @return The farm to which this crop belongs.
   */
  public Farm getFarm() {
    return farm;
  }

  /**
   * Set the farm to which this crop belongs.
   *
   * @param farm The farm to which this crop belongs.
   */
  public void setFarm(Farm farm) {
    this.farm = farm;
  }

  /**
   * Get the fertilizers used in this crop.
   *
   * @return The fertilizers used in this crop.
   */
  public List<Fertilizer> getFertilizers() {
    return fertilizers;
  }

  /**
   * Set the fertilizers used in this crop.
   *
   * @param fertilizers The fertilizers used in this crop.
   */
  public void setFertilizers(List<Fertilizer> fertilizers) {
    this.fertilizers = fertilizers;
  }
}
