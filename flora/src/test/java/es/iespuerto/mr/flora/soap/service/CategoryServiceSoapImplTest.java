package es.iespuerto.mr.flora.soap.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Category;
import es.iespuerto.mr.flora.service.CategoryServiceInterface;
import jakarta.xml.ws.WebServiceException;

public class CategoryServiceSoapImplTest {

    @Mock
    private CategoryServiceInterface categoryService;

    @InjectMocks
    private CategoryServiceSoapImpl categoryServiceSoap;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategoriesReturnsAllCategories() {
        Category category1 = new Category();
        category1.setName("Category 1");
        Category category2 = new Category();
        category2.setName("Category 2");

        when(categoryService.getAllCategories()).thenReturn(Arrays.asList(category1, category2));

        List<Category> categories = categoryServiceSoap.getAllCategories();

        assertEquals(2, categories.size());
        assertEquals("Category 1", categories.get(0).getName());
        assertEquals("Category 2", categories.get(1).getName());
    }

    @Test
    void getAllCategoriesThrowsWebServiceExceptionOnError() {
        when(categoryService.getAllCategories()).thenThrow(new RuntimeException("Error"));

        assertThrows(WebServiceException.class, () -> {
            categoryServiceSoap.getAllCategories();
        });
    }

    @Test
    void getCategoryByIdReturnsCategoryIfExists() throws ResourceNotFoundException {
        Category category = new Category();
        category.setId(1);
        category.setName("Test Category");

        when(categoryService.getCategoryById(1)).thenReturn(category);

        Category foundCategory = categoryServiceSoap.getCategoryById(1);

        assertEquals(1, foundCategory.getId());
        assertEquals("Test Category", foundCategory.getName());
    }

    @Test
    void getCategoryByIdThrowsWebServiceExceptionIfCategoryNotFound() throws ResourceNotFoundException {
        when(categoryService.getCategoryById(1)).thenThrow(new ResourceNotFoundException("Category not found"));

        assertThrows(WebServiceException.class, () -> {
            categoryServiceSoap.getCategoryById(1);
        });
    }

    @Test
    void createCategorySavesAndReturnsCategory() {
        Category category = new Category();
        category.setName("New Category");

        when(categoryService.createCategory(category)).thenReturn(category);

        Category createdCategory = categoryServiceSoap.createCategory(category);

        assertEquals("New Category", createdCategory.getName());
    }

    @Test
    void createCategoryThrowsWebServiceExceptionOnError() {
        Category category = new Category();
        category.setName("New Category");

        when(categoryService.createCategory(category)).thenThrow(new RuntimeException("Error"));

        assertThrows(WebServiceException.class, () -> {
            categoryServiceSoap.createCategory(category);
        });
    }



    @Test
    void updateCategoryThrowsWebServiceExceptionIfCategoryNotFound() throws ResourceNotFoundException {
        Category updatedDetails = new Category();
        updatedDetails.setName("Updated Category");

        when(categoryService.updateCategory(1, updatedDetails)).thenThrow(new ResourceNotFoundException("Category not found"));

        assertThrows(WebServiceException.class, () -> {
            categoryServiceSoap.updateCategory(1, updatedDetails);
        });
    }

    @Test
    void deleteCategoryDeletesCategoryIfExists() throws ResourceNotFoundException {
        doNothing().when(categoryService).deleteCategory(1);

        categoryServiceSoap.deleteCategory(1);

        verify(categoryService, times(1)).deleteCategory(1);
    }

    @Test
    void deleteCategoryThrowsWebServiceExceptionIfCategoryNotFound() throws ResourceNotFoundException {
        doThrow(new ResourceNotFoundException("Category not found")).when(categoryService).deleteCategory(1);

        assertThrows(WebServiceException.class, () -> {
            categoryServiceSoap.deleteCategory(1);
        });
    }
}