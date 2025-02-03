package es.iespuerto.mr.flora.soap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Category;
import es.iespuerto.mr.flora.service.CategoryServiceInterface;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;

/**
 * Implementation of the {@link CategoryServiceSoapInterface} that provides SOAP web services
 * for managing categories.
 * <p>
 * This class is annotated with {@code @WebService} to indicate that it is a web service endpoint.
 * It uses the {@link CategoryServiceInterface} to perform the actual operations, such as retrieving
 * all categories, retrieving a category by its ID, creating a new category, updating an existing category,
 * and deleting a category.
 * </p>
 * <p>
 * Exceptions are caught and wrapped in {@link WebServiceException} to provide meaningful error
 * messages to the clients.
 * </p>
 *
 * <p>Methods in this class:</p>
 * <ul>
 *   <li>{@link #getAllCategories()} - Retrieves all categories from the category service.</li>
 *   <li>{@link #getCategoryById(int)} - Retrieves a category by its ID.</li>
 *   <li>{@link #createCategory(Category)} - Creates a new category.</li>
 *   <li>{@link #updateCategory(int, Category)} - Updates an existing category.</li>
 *   <li>{@link #deleteCategory(int)} - Deletes a category by its ID.</li>
 * </ul>
 *
 * <p>Dependencies:</p>
 * <ul>
 *   <li>{@link CategoryServiceInterface} - Service interface that defines the core operations for categories.</li>
 * </ul>
 * 
 * <p>Exceptions:</p>
 * <ul>
 *   <li>{@link WebServiceException} - Thrown to provide detailed error messages to SOAP clients in case of failures.</li>
 *   <li>{@link ResourceNotFoundException} - Thrown when a requested category is not found.</li>
 * </ul>
 *
 * @see CategoryServiceSoapInterface
 * @see CategoryServiceInterface
 * @see WebServiceException
 */
@WebService(endpointInterface = "es.iespuerto.mr.flora.soap.service.CategoryServiceSoapInterface")
public class CategoryServiceSoapImpl implements CategoryServiceSoapInterface {

    private CategoryServiceInterface categoryService;

    /**
     * Sets the {@link CategoryServiceInterface} instance for performing the category operations.
     * 
     * @param categoryService The category service instance to be used by this SOAP service.
     */
    @Autowired
    public void setCategoryService(CategoryServiceInterface categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Retrieves all categories.
     * 
     * @return A list of all categories.
     * @throws WebServiceException If an error occurs while retrieving the categories.
     */
    @Override
    public List<Category> getAllCategories() {
        try {
            return categoryService.getAllCategories();
        } catch (Exception e) {
            throw new WebServiceException("Error obteniendo todas las categorías", e);
        }
    }

    /**
     * Retrieves a category by its ID.
     * 
     * @param categoryId The ID of the category to retrieve.
     * @return The category with the specified ID.
     * @throws WebServiceException If the category is not found or any other error occurs.
     */
    @Override
    public Category getCategoryById(int categoryId) {
        try {
            return categoryService.getCategoryById(categoryId);
        } catch (ResourceNotFoundException e) {
            throw new WebServiceException("Categoría no encontrada", e);
        } catch (Exception e) {
            throw new WebServiceException("Error obteniendo la categoría", e);
        }
    }

    /**
     * Creates a new category.
     * 
     * @param category The category to create.
     * @return The created category.
     * @throws WebServiceException If an error occurs while creating the category.
     */
    @Override
    public Category createCategory(Category category) {
        try {
            return categoryService.createCategory(category);
        } catch (Exception e) {
            throw new WebServiceException("Error creando la categoría", e);
        }
    }

    /**
     * Updates an existing category.
     * 
     * @param categoryId The ID of the category to update.
     * @param categoryDetails The new details of the category.
     * @return The updated category.
     * @throws WebServiceException If the category is not found or any other error occurs.
     */
    @Override
    public Category updateCategory(int categoryId, Category categoryDetails) {
        try {
            return categoryService.updateCategory(categoryId, categoryDetails);
        } catch (ResourceNotFoundException e) {
            throw new WebServiceException("Categoría no encontrada para actualizar", e);
        } catch (Exception e) {
            throw new WebServiceException("Error actualizando la categoría", e);
        }
    }

    /**
     * Deletes a category by its ID.
     * 
     * @param categoryId The ID of the category to delete.
     * @throws WebServiceException If the category is not found or any other error occurs.
     */
    @Override
    public void deleteCategory(int categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
        } catch (ResourceNotFoundException e) {
            throw new WebServiceException("Categoría no encontrada para eliminar", e);
        } catch (Exception e) {
            throw new WebServiceException("Error eliminando la categoría", e);
        }
    }
}
