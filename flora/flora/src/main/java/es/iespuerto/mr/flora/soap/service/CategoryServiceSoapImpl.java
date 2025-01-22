package es.iespuerto.mr.flora.soap.service;

import java.util.List;

import es.iespuerto.mr.flora.model.Category;
import es.iespuerto.mr.flora.service.CategoryServiceInterface;
import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;

@WebService(endpointInterface = "es.iespuerto.mr.flora.soap.service.CategoryServiceSoapInterface")
public class CategoryServiceSoapImpl implements CategoryServiceSoapInterface {

    private CategoryServiceInterface categoryService;

    @Autowired
    public void setCategoryService(CategoryServiceInterface categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public List<Category> getAllCategories() {
        try {
            return categoryService.getAllCategories();
        } catch (Exception e) {
            throw new WebServiceException("Error obteniendo todas las categorías", e);
        }
    }

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

    @Override
    public Category createCategory(Category category) {
        try {
            return categoryService.createCategory(category);
        } catch (Exception e) {
            throw new WebServiceException("Error creando la categoría", e);
        }
    }

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
