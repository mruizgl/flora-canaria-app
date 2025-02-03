package es.iespuerto.mr.flora.soap.service;

import java.util.List;

import es.iespuerto.mr.flora.model.Plant;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

/**
 * This interface defines the SOAP web service operations for managing plants.
 * It provides methods to retrieve, create, update, and delete plant records.
 * 
 */
@WebService(targetNamespace = "springboot.soap.service")
public interface PlantServiceSoapInterface {

    /**
     * Retrieves a list of all plants.
     * 
     * @return a list of Plant objects.
     */
    @WebMethod
    @WebResult(name = "plant")
    List<Plant> getAllPlants();

    /**
     * Retrieves a plant by its unique identifier.
     * 
     * @param plantId the unique identifier of the plant.
     * @return the Plant object with the specified ID.
     */
    @WebMethod
    Plant getPlantById(@WebParam(name = "plantId") int plantId);

    /**
     * Creates a new plant record.
     * 
     * @param plant the Plant object to be created.
     * @return the created Plant object.
     */
    @WebMethod
    Plant createPlant(@WebParam(name = "plant") Plant plant);

    /**
     * Updates an existing plant record.
     * 
     * @param plantId the unique identifier of the plant to be updated.
     * @param plantDetails the Plant object containing updated details.
     * @return the updated Plant object.
     */
    @WebMethod
    Plant updatePlant(@WebParam(name = "plantId") int plantId, @WebParam(name = "plantDetails") Plant plantDetails);

    /**
     * Deletes a plant by its unique identifier.
     * 
     * @param plantId the unique identifier of the plant to be deleted.
     */
    @WebMethod
    void deletePlant(@WebParam(name = "plantId") int plantId);
}