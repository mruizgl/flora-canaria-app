package es.iespuerto.mr.flora.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iespuerto.mr.flora.model.Role;

/**
 * Repository interface for managing {@link Role} entities.
 * This interface extends {@link JpaRepository} to provide CRUD operations
 * and additional query methods for the {@link Role} entity.
 * 
 * @see JpaRepository
 * @see Role
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
