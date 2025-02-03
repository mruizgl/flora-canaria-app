package es.iespuerto.mr.flora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.iespuerto.mr.flora.model.Location;

/**
 * Repository interface for managing {@link Location} entities.
 * Extends {@link JpaRepository} to provide CRUD operations and additional query methods.
 * 
 * @see JpaRepository
 * @see Location
 */
public interface LocationRepository extends JpaRepository<Location, Integer> {
}
