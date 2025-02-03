package es.iespuerto.mr.flora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iespuerto.mr.flora.model.Favorite;

/**
 * Repository interface for managing {@link Favorite} entities.
 * This interface extends {@link JpaRepository} to provide CRUD operations
 * and additional query methods for the Favorite entity.
 * 
 * <p>
 * The {@link Repository} annotation indicates that this interface is a 
 * Spring Data repository, which will be automatically implemented by Spring 
 * at runtime.
 * </p>
 * 
 * @see JpaRepository
 * @see Favorite
 */
@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
}
