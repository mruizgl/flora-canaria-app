package es.iespuerto.mr.flora.soap.service;


import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Favorite;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService(targetNamespace = "springboot.soap.service")
public interface FavoriteServiceSoapInterface {

    @WebMethod
    @WebResult(name = "favoritePlant")
    List<Favorite> getAllFavoritePlants();

    @WebMethod
    @WebResult(name = "favoritePlant")
    Favorite getFavoritePlantById(@WebParam(name = "id") int id);

    @WebMethod
    @WebResult(name = "favoritePlant")
    Favorite addFavoritePlant(@WebParam(name = "favoritePlant") Favorite favoritePlant);

    @WebMethod
    void deleteFavoritePlant(@WebParam(name = "id") int id) throws ResourceNotFoundException;
}
