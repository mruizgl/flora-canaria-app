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
    Category getCategoryById(@WebParam(name = "categoryId") int categoryId);
}