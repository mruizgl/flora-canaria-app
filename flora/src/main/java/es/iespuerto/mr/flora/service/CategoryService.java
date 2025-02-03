package es.iespuerto.mr.flora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Category;
import es.iespuerto.mr.flora.repository.CategoryRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * Service class for managing categories.
 * Provides methods to perform CRUD (Create, Read, Update, Delete) operations on categories.
 * 
 * <p>This class is annotated with {@code @Component} to indicate that it is a Spring component,
 * and {@code @Slf4j} to enable logging.</p>
 * 
 * <p>Implements the {@link CategoryServiceInterface} interface to define the business logic for
 * managing category entities.</p>
 * 
 * <p>Methods in this class:</p>
 * <ul>
 *   <li>{@link #getAllCategories()} - Fetches all categories from the repository.</li>
 *   <li>{@link #getCategoryById(int)} - Fetches a category by its ID. Throws a {@link ResourceNotFoundException} if not found.</li>
 *   <li>{@link #createCategory(Category)} - Creates a new category in the repository.</li>
 *   <li>{@link #updateCategory(int, Category)} - Updates an existing category's details. Throws a {@link ResourceNotFoundException} if the category is not found.</li>
 *   <li>{@link #deleteCategory(int)} - Deletes a category by its ID. Throws a {@link ResourceNotFoundException} if the category is not found.</li>
 * </ul>
 * 
 * <p>Dependencies:</p>
 * <ul>
 *   <li>{@link CategoryRepository} - The repository used for category data access and manipulation.</li>
 * </ul>
 * 
 * <p>Exceptions:</p>
 * <ul>
 *   <li>{@link ResourceNotFoundException} - Thrown when a category is not found for a given ID.</li>
 * </ul>
 * 
 * <p>Annotations:</p>
 * <ul>
 *   <li>{@code @Autowired} - Used to inject the {@link CategoryRepository} dependency into the service.</li>
 *   <li>{@code @PathVariable} - Used to bind the category ID from the URL path to method parameters.</li>
 *   <li>{@code @Valid} - Used to validate the {@link Category} object in the request body.</li>
 *   <li>{@code @RequestBody} - Indicates that the method parameter should be bound to the body of the web request.</li>
 * </ul>
 * 
 * @see CategoryServiceInterface
 * @see CategoryRepository
 * @see ResourceNotFoundException
 */
@Slf4j
@Component
public class CategoryService implements CategoryServiceInterface {

    private CategoryRepository categoryRepository;

    /**
     * Sets the {@link CategoryRepository} dependency.
     * 
     * @param categoryRepository the repository to be used for category data access.
     */
    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Fetches all categories from the repository.
     * 
     * @return a list of all categories.
     */
    @Override
    public List<Category> getAllCategories() {
        log.info("Fetching all categories");
        return categoryRepository.findAll();
    }

    /**
     * Fetches a category by its ID.
     * 
     * @param categoryId the ID of the category to fetch.
     * @return the category with the specified ID.
     * @throws ResourceNotFoundException if the category is not found.
     */
    @Override
    public Category getCategoryById(@PathVariable(value = "id") int categoryId) throws ResourceNotFoundException {
        log.info("Fetching category by id: {}", categoryId);
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));
    }

    /**
     * Creates a new category in the repository.
     * 
     * @param category the category to create.
     * @return the created category.
     */
    @Override
    public Category createCategory(@Valid @RequestBody Category category) {
        log.info("Creating new category: {}", category);
        return categoryRepository.save(category);
    }

    /**
     * Updates an existing category based on the provided category ID and details.
     * 
     * @param categoryId the ID of the category to update.
     * @param categoryDetails the new details of the category.
     * @return the updated category.
     * @throws ResourceNotFoundException if the category is not found.
     */
    @Override
    public Category updateCategory(@PathVariable(value = "id") int categoryId, @Valid @RequestBody Category categoryDetails) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));

        category.setName(categoryDetails.getName());
        log.info("Updating category with id: {}", categoryId);
        return categoryRepository.save(category);
    }

    /**
     * Deletes a category by its ID.
     * 
     * @param categoryId the ID of the category to delete.
     * @throws ResourceNotFoundException if the category is not found.
     */
    @Override
    public void deleteCategory(@PathVariable(value = "id") int categoryId) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));

        log.info("Deleting category with id: {}", categoryId);
        categoryRepository.delete(category);
    }
}
