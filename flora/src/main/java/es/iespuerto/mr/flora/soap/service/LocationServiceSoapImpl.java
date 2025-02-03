package es.iespuerto.mr.flora.soap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.iespuerto.mr.flora.model.Location;
import es.iespuerto.mr.flora.service.LocationServiceInterface;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;

/**
 * Implementation of the {@link LocationServiceSoapInterface}.
 * This class provides SOAP web service methods for managing {@link Location} entities.
 * It delegates the actual CRUD operations to the {@link LocationServiceInterface}.
 * 
 * <p>Each method in this service interacts with the underlying {@link LocationServiceInterface} 
 * to perform the required actions. In case of an error, methods throw a {@link WebServiceException}
 * with an appropriate message.</p>
 * 
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@link WebService} - Specifies the endpoint interface for the SOAP web service.</li>
 *   <li>{@link Autowired} - Injects the implementation of {@link LocationServiceInterface} into this class.</li>
 * </ul>
 * 
 * <p>Methods provided:</p>
 * <ul>
 *   <li>{@link #getAllLocations()} - Retrieves all locations.</li>
 *   <li>{@link #getLocationById(int)} - Retrieves a location by its ID.</li>
 *   <li>{@link #createLocation(Location)} - Creates a new location.</li>
 *   <li>{@link #updateLocation(int, Location)} - Updates an existing location.</li>
 *   <li>{@link #deleteLocation(int)} - Deletes a location by its ID.</li>
 * </ul>
 * 
 * <p>Each method may throw a {@link WebServiceException} if the underlying service encounters an error.</p>
 * 
 * @see LocationServiceSoapInterface
 * @see LocationServiceInterface
 * @see Location
 * @see WebServiceException
 */
@WebService(endpointInterface = "es.iespuerto.mr.flora.soap.service.LocationServiceSoapInterface")
public class LocationServiceSoapImpl implements LocationServiceSoapInterface {

    private LocationServiceInterface locationService;

    /**
     * Sets the {@link LocationServiceInterface} instance to be used by this SOAP service.
     * This method is called automatically by Spring's dependency injection.
     * 
     * @param locationService the {@link LocationServiceInterface} implementation.
     */
    @Autowired
    public void setLocationService(LocationServiceInterface locationService) {
        this.locationService = locationService;
    }

    /**
     * Retrieves all locations from the underlying service.
     * 
     * @return a list of {@link Location} entities.
     */
    @Override
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    /**
     * Retrieves a location by its ID from the underlying service.
     * 
     * @param locationId the ID of the location to retrieve.
     * @return the {@link Location} entity with the specified ID.
     * @throws WebServiceException if an error occurs while retrieving the location.
     */
    @Override
    public Location getLocationById(int locationId) {
        try {
            return locationService.getLocationById(locationId);
        } catch (Exception e) {
            throw new WebServiceException("Error obteniendo la ubicación", e);
        }
    }

    /**
     * Creates a new location by delegating to the underlying service.
     * 
     * @param location the {@link Location} entity to create.
     * @return the created {@link Location} entity.
     * @throws WebServiceException if an error occurs while creating the location.
     */
    @Override
    public Location createLocation(Location location) {
        try {
            return locationService.createLocation(location);
        } catch (Exception e) {
            throw new WebServiceException("Error creando la ubicación", e);
        }
    }

    /**
     * Updates an existing location by delegating to the underlying service.
     * 
     * @param locationId the ID of the location to update.
     * @param locationDetails the new {@link Location} details to update.
     * @return the updated {@link Location} entity.
     * @throws WebServiceException if an error occurs while updating the location.
     */
    @Override
    public Location updateLocation(int locationId, Location locationDetails) {
        try {
            return locationService.updateLocation(locationId, locationDetails);
        } catch (Exception e) {
            throw new WebServiceException("Error actualizando la ubicación", e);
        }
    }

    /**
     * Deletes a location by its ID by delegating to the underlying service.
     * 
     * @param locationId the ID of the location to delete.
     * @throws WebServiceException if an error occurs while deleting the location.
     */
    @Override
    public void deleteLocation(int locationId) {
        try {
            locationService.deleteLocation(locationId);
        } catch (Exception e) {
            throw new WebServiceException("Error eliminando la ubicación", e);
        }
    }
}
