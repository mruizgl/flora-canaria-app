package es.iespuerto.mr.flora.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Category;
import es.iespuerto.mr.flora.repository.CategoryRepository;

public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

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

        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category1, category2));

        List<Category> categories = categoryService.getAllCategories();

        assertEquals(2, categories.size());
        assertEquals("Category 1", categories.get(0).getName());
        assertEquals("Category 2", categories.get(1).getName());
    }

    @Test
    void getCategoryByIdReturnsCategoryIfExists() throws ResourceNotFoundException {
        Category category = new Category();
        category.setId(1);
        category.setName("Test Category");

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));

        Category foundCategory = categoryService.getCategoryById(1);

        assertEquals(1, foundCategory.getId());
        assertEquals("Test Category", foundCategory.getName());
    }

    @Test
    void getCategoryByIdThrowsExceptionIfCategoryDoesNotExist() {
        when(categoryRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            categoryService.getCategoryById(1);
        });
    }

    @Test
    void createCategorySavesAndReturnsCategory() {
        Category category = new Category();
        category.setName("New Category");

        when(categoryRepository.save(category)).thenReturn(category);

        Category createdCategory = categoryService.createCategory(category);

        assertEquals("New Category", createdCategory.getName());
    }

    @Test
    void updateCategoryUpdatesAndReturnsCategory() throws ResourceNotFoundException {
        Category existingCategory = new Category();
        existingCategory.setId(1);
        existingCategory.setName("Existing Category");

        Category updatedDetails = new Category();
        updatedDetails.setName("Updated Category");

        when(categoryRepository.findById(1)).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.save(existingCategory)).thenReturn(existingCategory);

        Category updatedCategory = categoryService.updateCategory(1, updatedDetails);

        assertEquals("Updated Category", updatedCategory.getName());
    }

    @Test
    void updateCategoryThrowsExceptionIfCategoryDoesNotExist() {
        Category updatedDetails = new Category();
        updatedDetails.setName("Updated Category");

        when(categoryRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            categoryService.updateCategory(1, updatedDetails);
        });
    }

    @Test
    void deleteCategoryDeletesCategoryIfExists() throws ResourceNotFoundException {
        Category category = new Category();
        category.setId(1);
        category.setName("Category to Delete");

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        doNothing().when(categoryRepository).delete(category);

        categoryService.deleteCategory(1);

        verify(categoryRepository, times(1)).delete(category);
    }

    @Test
    void deleteCategoryThrowsExceptionIfCategoryDoesNotExist() {
        when(categoryRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            categoryService.deleteCategory(1);
        });
    }
}