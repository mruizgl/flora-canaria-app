package es.iespuerto.mr.flora.soap.service;

import es.iespuerto.mr.flora.model.Category;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService(targetNamespace = "springboot.soap.service")
public interface CategoryServiceSoapInterface {

    @WebMethod
    @WebResult(name = "category")
    List<Category> getAllCategories();

    @WebMethod
    @WebResult(name = "category")
    Category getCategoryById(@WebParam(name = "categoryId") int categoryId);

    @WebMethod
    @WebResult(name = "category")
    Category createCategory(@WebParam(name = "category") Category category);

    @WebMethod
    @WebResult(name = "category")
    Category updateCategory(@WebParam(name = "categoryId") int categoryId,
                            @WebParam(name = "categoryDetails") Category categoryDetails);

    @WebMethod
    void deleteCategory(@WebParam(name = "categoryId") int categoryId);
}
