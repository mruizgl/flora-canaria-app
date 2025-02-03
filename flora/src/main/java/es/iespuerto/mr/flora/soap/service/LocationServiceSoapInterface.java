package es.iespuerto.mr.flora.soap.service;

import java.util.List;

import es.iespuerto.mr.flora.model.Location;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

/**
 * This interface defines the SOAP web service operations for managing locations.
 * It provides methods to retrieve, create, update, and delete locations.
 */
@WebService(targetNamespace = "springboot.soap.service")
public interface LocationServiceSoapInterface {

    /**
     * Retrieves a list of all locations.
     * 
     * @return a list of {@link Location} objects.
     */
    @WebMethod
    @WebResult(name = "location")
    List<Location> getAllLocations();

    /**
     * Retrieves a location by its ID.
     * 
     * @param locationId the ID of the location to retrieve.
     * @return the {@link Location} object with the specified ID.
     */
    @WebMethod
    @WebResult(name = "location")
    Location getLocationById(@WebParam(name = "locationId") int locationId);

    /**
     * Creates a new location.
     * 
     * @param location the {@link Location} object to create.
     * @return the created {@link Location} object.
     */
    @WebMethod
    @WebResult(name = "location")
    Location createLocation(@WebParam(name = "location") Location location);

    /**
     * Updates an existing location.
     * 
     * @param locationId the ID of the location to update.
     * @param locationDetails the new details of the location.
     * @return the updated {@link Location} object.
     */
    @WebMethod
    @WebResult(name = "location")
    Location updateLocation(@WebParam(name = "locationId") int locationId,
                            @WebParam(name = "locationDetails") Location locationDetails);

    /**
     * Deletes a location by its ID.
     * 
     * @param locationId the ID of the location to delete.
     */
    @WebMethod
    void deleteLocation(@WebParam(name = "locationId") int locationId);
}
