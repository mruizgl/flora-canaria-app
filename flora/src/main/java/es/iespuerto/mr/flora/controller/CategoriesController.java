package es.iespuerto.mr.flora.controller;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Category;
import es.iespuerto.mr.flora.service.CategoryServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador que maneja las operaciones relacionadas con las categorías.
 * Permite obtener, crear, actualizar y eliminar categorías.
 * <p>
 * @author Melissa Ruiz (@mruizgl en github)
 */
/**
 * Controlador REST para manejar las operaciones relacionadas con las categorías.
 * Proporciona endpoints para obtener, crear, actualizar y eliminar categorías.
 * 
 * Endpoints disponibles:
 * - GET /api/v1/categories/ : Obtiene todas las categorías disponibles.
 * - GET /api/v1/category/{id} : Obtiene una categoría específica por su ID.
 * - POST /api/v1/add/category/ : Crea una nueva categoría.
 * - PUT /api/v1/update/category/{id} : Actualiza los detalles de una categoría existente.
 * - DELETE /api/v1/delete/category/{id} : Elimina una categoría específica por su ID.
 * 
 * Utiliza un servicio de categorías para realizar las operaciones necesarias.
 * 
 * @see CategoryServiceInterface
 * @see Category
 * @see ResourceNotFoundException
 */
@RestController
@RequestMapping("/api/v1")
public class CategoriesController {

    private CategoryServiceInterface categoryService;

    /**
     * Constructor que inyecta el servicio de categorías.
     *
     * @param categoryService Servicio para manejar las categorías.
     */
    @Autowired
    public void setCategoryService(CategoryServiceInterface categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Obtiene todas las categorías disponibles.
     *
     * @return Lista de categorías.
     */
    @Operation(summary = "Get all categories")
    @GetMapping("/categories/")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    /**
     * Obtiene una categoría específica por su ID.
     *
     * @param categoryId El ID de la categoría a obtener.
     * @return La categoría correspondiente al ID proporcionado.
     * @throws ResourceNotFoundException Si la categoría no se encuentra.
     */
    @Operation(summary = "Get category by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") int categoryId) throws ResourceNotFoundException {
        Category category = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok().body(category);
    }

    /**
     * Crea una nueva categoría.
     *
     * @param category Los detalles de la categoría a crear.
     * @return La categoría creada.
     */
    @Operation(summary = "Insert category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add/category/")
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    /**
     * Actualiza los detalles de una categoría existente.
     *
     * @param categoryId El ID de la categoría a actualizar.
     * @param categoryDetails Los nuevos detalles de la categoría.
     * @return La categoría actualizada.
     * @throws ResourceNotFoundException Si la categoría no se encuentra.
     */
    @Operation(summary = "Update category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @PutMapping("/update/category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") int categoryId,
                                                   @Valid @RequestBody Category categoryDetails) throws ResourceNotFoundException {
        final Category updatedCategory = categoryService.updateCategory(categoryId, categoryDetails);
        return ResponseEntity.ok(updatedCategory);
    }

    /**
     * Elimina una categoría específica por su ID.
     *
     * @param categoryId El ID de la categoría a eliminar.
     * @return Un mapa indicando si la categoría fue eliminada exitosamente.
     * @throws ResourceNotFoundException Si la categoría no se encuentra.
     */
    @Operation(summary = "Delete category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @DeleteMapping("/delete/category/{id}")
    public Map<String, Boolean> deleteCategory(@PathVariable(value = "id") int categoryId) throws ResourceNotFoundException {
        categoryService.deleteCategory(categoryId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
