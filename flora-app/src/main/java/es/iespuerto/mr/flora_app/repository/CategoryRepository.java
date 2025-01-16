package es.iespuerto.mr.flora_app.repository;


import es.iespuerto.mr.flora_app.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora_app.model.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> getAllCategories();
    Category getCategoryById(int categoryId) throws ResourceNotFoundException;
    Category createCategory(Category category);
    Category updateCategory(int categoryId, Category categoryDetails) throws ResourceNotFoundException;
    void deleteCategory(int categoryId) throws ResourceNotFoundException;
}
