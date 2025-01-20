package es.iespuerto.mr.flora.service;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Category;
import es.iespuerto.mr.flora.model.User;

import java.util.List;

public interface CategoryServiceInterface {
    List<Category> getAllCategories();
    Category getCategoryById(int categoryId) throws ResourceNotFoundException;
    Category createCategory(Category category);
    Category updateCategory(int categoryId, Category categoryDetails)       throws ResourceNotFoundException;
    void deleteCategory(int categoryid) throws ResourceNotFoundException;
}
