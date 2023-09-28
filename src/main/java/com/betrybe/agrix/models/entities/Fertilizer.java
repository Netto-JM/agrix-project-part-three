package com.betrybe.agrix.models.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 * Represents a fertilizer entity.
 */
@Entity
@Table(name = "fertilizer")
public class Fertilizer {
  /**
   * The unique identifier of the fertilizer.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * The name of the fertilizer.
   */
  private String name;

  /**
   * The brand of the fertilizer.
   */
  private String brand;

  /**
   * The composition of the fertilizer.
   */
  private String composition;

  /**
   * The crops to which this fertilizer is applied.
   */
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinTable(
      name = "crop_fertilizer",
      joinColumns = @JoinColumn(name = "fertilizer_id"),
      inverseJoinColumns = @JoinColumn(name = "crop_id")
  )
  private List<Crop> crops;

  /**
   * Default constructor for Fertilizer.
   */
  public Fertilizer() {}

  /**
   * Parameterized constructor for Fertilizer.
   *
   * @param id          The unique identifier of the fertilizer.
   * @param name        The name of the fertilizer.
   * @param brand       The brand of the fertilizer.
   * @param composition The composition of the fertilizer.
   * @param crops       The crops to which this fertilizer is applied.
   */
  public Fertilizer(Long id, String name, String brand, String composition, List<Crop> crops) {
    this.id = id;
    this.name = name;
    this.brand = brand;
    this.composition = composition;
    this.crops = crops;
  }

  /**
   * Get the unique identifier of the fertilizer.
   *
   * @return The unique identifier of the fertilizer.
   */
  public Long getId() {
    return id;
  }

  /**
   * Set the unique identifier of the fertilizer.
   *
   * @param id The unique identifier of the fertilizer.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Get the name of the fertilizer.
   *
   * @return The name of the fertilizer.
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of the fertilizer.
   *
   * @param name The name of the fertilizer.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the brand of the fertilizer.
   *
   * @return The brand of the fertilizer.
   */
  public String getBrand() {
    return brand;
  }

  /**
   * Set the brand of the fertilizer.
   *
   * @param brand The brand of the fertilizer.
   */
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   * Get the composition of the fertilizer.
   *
   * @return The composition of the fertilizer.
   */
  public String getComposition() {
    return composition;
  }

  /**
   * Set the composition of the fertilizer.
   *
   * @param composition The composition of the fertilizer.
   */
  public void setComposition(String composition) {
    this.composition = composition;
  }

  /**
   * Get the crops to which this fertilizer is applied.
   *
   * @return The crops to which this fertilizer is applied.
   */
  public List<Crop> getCrops() {
    return crops;
  }

  /**
   * Set the crops to which this fertilizer is applied.
   *
   * @param crops The crops to which this fertilizer is applied.
   */
  public void setCrops(List<Crop> crops) {
    this.crops = crops;
  }
}
