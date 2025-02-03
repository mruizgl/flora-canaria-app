package es.iespuerto.mr.flora.controller;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Category;
import es.iespuerto.mr.flora.service.CategoryServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoriesControllerTest {

    @Mock
    private CategoryServiceInterface categoryService;

    @InjectMocks
    private CategoriesController categoriesController;

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1);
        category.setName("Test Category");
    }

    @Test
    void getAllCategoriesReturnsListOfCategories() {
        when(categoryService.getAllCategories()).thenReturn(Arrays.asList(category));

        List<Category> categories = categoriesController.getAllCategories();

        assertNotNull(categories);
        assertEquals(1, categories.size());
        assertEquals("Test Category", categories.get(0).getName());
    }

    @Test
    void getCategoryByIdReturnsCategory() throws ResourceNotFoundException {
        when(categoryService.getCategoryById(1)).thenReturn(category);

        ResponseEntity<Category> response = categoriesController.getCategoryById(1);

        assertNotNull(response.getBody());
        assertEquals("Test Category", response.getBody().getName());
    }

    @Test
    void getCategoryByIdThrowsResourceNotFoundException() throws ResourceNotFoundException {
        when(categoryService.getCategoryById(1)).thenThrow(new ResourceNotFoundException("Category not found"));

        assertThrows(ResourceNotFoundException.class, () -> categoriesController.getCategoryById(1));
    }

    @Test
    void createCategoryReturnsCreatedCategory() {
        when(categoryService.createCategory(any(Category.class))).thenReturn(category);

        Category createdCategory = categoriesController.createCategory(category);

        assertNotNull(createdCategory);
        assertEquals("Test Category", createdCategory.getName());
    }

    @Test
    void updateCategoryReturnsUpdatedCategory() throws ResourceNotFoundException {
        when(categoryService.updateCategory(eq(1), any(Category.class))).thenReturn(category);

        ResponseEntity<Category> response = categoriesController.updateCategory(1, category);

        assertNotNull(response.getBody());
        assertEquals("Test Category", response.getBody().getName());
    }

    @Test
    void updateCategoryThrowsResourceNotFoundException() throws ResourceNotFoundException {
        when(categoryService.updateCategory(eq(1), any(Category.class))).thenThrow(new ResourceNotFoundException("Category not found"));

        assertThrows(ResourceNotFoundException.class, () -> categoriesController.updateCategory(1, category));
    }

    @Test
    void deleteCategoryReturnsSuccessMap() throws ResourceNotFoundException {
        doNothing().when(categoryService).deleteCategory(1);

        Map<String, Boolean> response = categoriesController.deleteCategory(1);

        assertTrue(response.get("deleted"));
    }

    @Test
    void deleteCategoryThrowsResourceNotFoundException() throws ResourceNotFoundException {
        doThrow(new ResourceNotFoundException("Category not found")).when(categoryService).deleteCategory(1);

        assertThrows(ResourceNotFoundException.class, () -> categoriesController.deleteCategory(1));
    }
}