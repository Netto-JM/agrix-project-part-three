package com.betrybe.agrix.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 * Represents a farm entity.
 */
@Entity
@Table(name = "farm")
public class Farm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * The name of the farm.
   */
  private String name;

  /**
   * The size of the farm in acres.
   */
  private Double size;

  @OneToMany(mappedBy = "farm")
  @JsonIgnore
  private List<Crop> crops;

  // Construtores, getters e setters

  // Construtor vazio
  public Farm() {}

  // Construtor parametrizado
  /**
   * Parameterized constructor for the Farm entity.
   *
   * @param id   The unique identifier of the farm.
   * @param name The name of the farm.
   * @param size The size of the farm in acres.
   * @param crops The list of crops associated with the farm.
   */
  public Farm(Long id, String name, Double size, List<Crop> crops) {
    this.id = id;
    this.name = name;
    this.size = size;
    this.crops = crops;
  }

  /**
   * Get the unique identifier of the farm.
   *
   * @return The farm's ID.
   */
  public Long getId() {
    return id;
  }

  /**
   * Set the unique identifier of the farm.
   *
   * @param id The farm's ID.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Get the name of the farm.
   *
   * @return The name of the farm.
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of the farm.
   *
   * @param name The farm's name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the size of the farm in acres.
   *
   * @return The size of the farm.
   */
  public Double getSize() {
    return size;
  }

  /**
   * Set the size of the farm in acres.
   *
   * @param size The size of the farm.
   */
  public void setSize(Double size) {
    this.size = size;
  }

  /**
   * Get the list of crops associated with the farm.
   *
   * @return A list of Crop objects.
   */
  public List<Crop> getCrops() {
    return crops;
  }

  /**
   * Set the list of crops associated with the farm.
   *
   * @param crops A list of Crop objects.
   */
  public void setCrops(List<Crop> crops) {
    this.crops = crops;
  }
}
