package es.iespuerto.mr.flora.service;

import java.util.List;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Category;
import es.iespuerto.mr.flora.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CategoryService implements CategoryServiceInterface {

    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }



    @Override
    public List<Category> getAllCategories() {
        log.info("Fetching all categories");
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(@PathVariable(value = "id") int categoryId) throws ResourceNotFoundException {
        log.info("Fetching category by id: {}", categoryId);
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));
    }

    @Override
    public Category createCategory(@Valid @RequestBody Category category) {
        log.info("Creating new category: {}", category);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(@PathVariable(value = "id") int categoryId, @Valid @RequestBody Category categoryDetails) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));

        category.setName(categoryDetails.getName());
        log.info("Updating category with id: {}", categoryId);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(@PathVariable(value = "id") int categoryid) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(categoryid)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryid));

        log.info("Deleting category with id: {}", categoryid);
        categoryRepository.delete(category);
    }
}