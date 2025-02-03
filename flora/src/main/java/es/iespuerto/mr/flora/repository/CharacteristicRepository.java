package es.iespuerto.mr.flora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iespuerto.mr.flora.model.Characteristic;

/**
 * Repository interface for managing {@link Characteristic} entities.
 * Extends {@link JpaRepository} to provide CRUD operations and additional
 * JPA functionalities.
 * 
 * <p>This interface is annotated with {@link Repository}, which indicates
 * that it is a Spring Data repository and enables exception translation
 * into Spring's DataAccessException hierarchy.
 * 
 * @see JpaRepository
 * @see Characteristic
 */
@Repository
public interface CharacteristicRepository extends JpaRepository<Characteristic, Integer> {
}
