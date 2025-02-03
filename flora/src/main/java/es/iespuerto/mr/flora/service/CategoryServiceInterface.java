package es.iespuerto.mr.flora.service;

import java.util.List;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Category;

/**
 * Interface for Category Service operations.
 * Provides methods to perform CRUD (Create, Read, Update, Delete) operations on Category entities.
 * 
 * This interface defines the basic operations for managing categories in the system,
 * including retrieving, creating, updating, and deleting category records.
 * 
 * Exceptions:
 * <ul>
 *   <li><strong>ResourceNotFoundException</strong> - Thrown when a category is not found during retrieval or update operations.</li>
 * </ul>
 * 
 * Methods:
 * <ul>
 *   <li><strong>getAllCategories()</strong> - Retrieves all categories from the system.</li>
 *   <li><strong>getCategoryById(int categoryId)</strong> - Retrieves a category by its ID. Throws a <code>ResourceNotFoundException</code> if the category is not found.</li>
 *   <li><strong>createCategory(Category category)</strong> - Creates a new category in the system.</li>
 *   <li><strong>updateCategory(int categoryId, Category categoryDetails)</strong> - Updates an existing category based on the provided category ID and new details. Throws a <code>ResourceNotFoundException</code> if the category is not found.</li>
 *   <li><strong>deleteCategory(int categoryId)</strong> - Deletes a category by its ID. Throws a <code>ResourceNotFoundException</code> if the category is not found.</li>
 * </ul>
 */
public interface CategoryServiceInterface {

    /**
     * Retrieves all categories.
     * 
     * @return a list of all categories.
     */
    List<Category> getAllCategories();

    /**
     * Retrieves a category by its ID.
     * 
     * @param categoryId the ID of the category to retrieve.
     * @return the category with the specified ID.
     * @throws ResourceNotFoundException if the category is not found.
     */
    Category getCategoryById(int categoryId) throws ResourceNotFoundException;

    /**
     * Creates a new category.
     * 
     * @param category the category to create.
     * @return the created category.
     */
    Category createCategory(Category category);

    /**
     * Updates an existing category.
     * 
     * @param categoryId the ID of the category to update.
     * @param categoryDetails the new details of the category.
     * @return the updated category.
     * @throws ResourceNotFoundException if the category is not found.
     */
    Category updateCategory(int categoryId, Category categoryDetails) throws ResourceNotFoundException;

    /**
     * Deletes a category by its ID.
     * 
     * @param categoryId the ID of the category to delete.
     * @throws ResourceNotFoundException if the category is not found.
     */
    void deleteCategory(int categoryId) throws ResourceNotFoundException;
}
