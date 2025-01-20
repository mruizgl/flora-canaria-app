package es.iespuerto.mr.flora.soap.service;

import java.util.List;

import es.iespuerto.mr.flora.model.Category;
import es.iespuerto.mr.flora.service.CategoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;

@WebService(endpointInterface = "es.iespuerto.mr.flora.soap.service.CategoryServiceSoapInterface")
public class CategoryServiceSoapImpl implements CategoryServiceSoapInterface{
    private CategoryServiceInterface categoryService;

    @Autowired
    public void setCategoryService(CategoryServiceInterface categoryService) {
        this.categoryService = categoryService;
    }

    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    public Category getCategoryById(int categoryId) {
        try {
            return categoryService.getCategoryById(categoryId);
        } catch (Exception e) {
            throw new WebServiceException("Error obteniendo la categoría", e);
        }
    }
}
