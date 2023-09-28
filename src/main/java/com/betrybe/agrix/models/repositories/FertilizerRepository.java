package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing Fertilizer entities.
 */
@Repository
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {

}
