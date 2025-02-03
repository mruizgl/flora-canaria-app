package es.iespuerto.mr.flora.soap.service;

import java.util.List;

import es.iespuerto.mr.flora.model.Category;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

/**
 * This interface defines the SOAP web service operations for managing categories.
 * It provides methods to retrieve, create, update, and delete categories.
 * 
 */
@WebService(targetNamespace = "springboot.soap.service")
public interface CategoryServiceSoapInterface {

    /**
     * Retrieves all categories.
     * 
     * @return a list of all categories.
     */
    @WebMethod
    @WebResult(name = "category")
    List<Category> getAllCategories();

    /**
     * Retrieves a category by its ID.
     * 
     * @param categoryId the ID of the category to retrieve.
     * @return the category with the specified ID.
     */
    @WebMethod
    @WebResult(name = "category")
    Category getCategoryById(@WebParam(name = "categoryId") int categoryId);

    /**
     * Creates a new category.
     * 
     * @param category the category to create.
     * @return the created category.
     */
    @WebMethod
    @WebResult(name = "category")
    Category createCategory(@WebParam(name = "category") Category category);

    /**
     * Updates an existing category.
     * 
     * @param categoryId the ID of the category to update.
     * @param categoryDetails the new details of the category.
     * @return the updated category.
     */
    @WebMethod
    @WebResult(name = "category")
    Category updateCategory(@WebParam(name = "categoryId") int categoryId,
                            @WebParam(name = "categoryDetails") Category categoryDetails);

    /**
     * Deletes a category by its ID.
     * 
     * @param categoryId the ID of the category to delete.
     */
    @WebMethod
    void deleteCategory(@WebParam(name = "categoryId") int categoryId);
}
