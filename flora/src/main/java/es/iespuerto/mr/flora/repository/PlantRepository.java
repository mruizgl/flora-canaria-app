package es.iespuerto.mr.flora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iespuerto.mr.flora.model.Plant;

/**
 * Repository interface for managing {@link Plant} entities.
 * Extends {@link JpaRepository} to provide CRUD operations and additional
 * JPA functionalities for the Plant entity.
 * 
 * @see JpaRepository
 * @see Plant
 */
@Repository
public interface PlantRepository extends JpaRepository<Plant, Integer> {
}
