package es.iespuerto.mr.flora.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iespuerto.mr.flora.model.User;

/**
 * Repository interface for managing {@link User} entities.
 * <p>This interface extends {@link JpaRepository} to provide CRUD operations and additional query methods for the {@link User} entity.</p>
 * <p>By extending {@link JpaRepository}, this interface inherits several methods for performing operations such as save, find, and delete.</p>
 * 
 * <p>Custom query methods:</p>
 * <ul>
 *   <li>{@link #findByName(String)} - Finds a {@link User} by their name.</li>
 * </ul>
 * 
 * <p>Annotations:</p>
 * <ul>
 *   <li>{@code @Repository} - Marks this interface as a Spring repository, enabling it to be detected and managed by the Spring container.</li>
 * </ul>
 * 
 * @see JpaRepository
 * @see User
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    /**
     * Finds a user by their name.
     * 
     * @param name The name of the user to be found.
     * @return An {@link Optional} containing the found user, or empty if no user is found with the given name.
     */
    Optional<User> findByName(String name);
}
