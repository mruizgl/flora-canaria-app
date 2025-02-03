package es.iespuerto.mr.flora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.iespuerto.mr.flora.model.Category;

/**
 * Repository interface for managing {@link Category} entities.
 * Extends {@link JpaRepository} to provide CRUD operations and additional
 * JPA functionalities.
 * 
 * @see JpaRepository
 * @see Category
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
