package es.iespuerto.mr.flora.service;

import java.util.List;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Location;

/**
 * Interface for Location Service operations.
 * This interface provides methods to perform CRUD (Create, Read, Update, Delete) operations 
 * on {@link Location} entities.
 * 
 * The methods defined in this interface are intended to be implemented by a service class 
 * that interacts with the data source to manage {@code Location} objects.
 * 
 * CRUD Operations:
 * <ul>
 *   <li>{@code getAllLocations()} - Retrieves a list of all location entities.</li>
 *   <li>{@code getLocationById(int locationId)} - Retrieves a location entity by its unique identifier.</li>
 *   <li>{@code createLocation(Location location)} - Creates a new location entity.</li>
 *   <li>{@code updateLocation(int locationId, Location locationDetails)} - Updates an existing location entity.</li>
 *   <li>{@code deleteLocation(int locationId)} - Deletes a location entity by its unique identifier.</li>
 * </ul>
 * 
 * Exception Handling:
 * <ul>
 *   <li>{@link ResourceNotFoundException} - Thrown when a resource (e.g., location) is not found.</li>
 * </ul>
 * 
 * Methods:
 * <ul>
 *   <li><strong>getAllLocations()</strong> - Retrieves all locations from the database.</li>
 *   <li><strong>getLocationById(int locationId)</strong> - Retrieves a location by its unique identifier.</li>
 *   <li><strong>createLocation(Location location)</strong> - Creates a new location and stores it in the database.</li>
 *   <li><strong>updateLocation(int locationId, Location locationDetails)</strong> - Updates the location with the specified id.</li>
 *   <li><strong>deleteLocation(int locationId)</strong> - Deletes the location with the specified id.</li>
 * </ul>
 * 
 * @author Melissa Ruiz
 */
public interface LocationServiceInterface {
    /**
     * Retrieves all location entities.
     * 
     * @return a list of {@code Location} entities.
     */
    List<Location> getAllLocations();

    /**
     * Retrieves a location entity by its unique identifier.
     * 
     * @param locationId the ID of the location to retrieve.
     * @return the {@code Location} entity.
     * @throws ResourceNotFoundException if the location with the specified ID is not found.
     */
    Location getLocationById(int locationId) throws ResourceNotFoundException;

    /**
     * Creates a new location entity and stores it in the database.
     * 
     * @param location the {@code Location} entity to create.
     * @return the created {@code Location} entity.
     */
    Location createLocation(Location location);

    /**
     * Updates an existing location entity with the specified ID.
     * 
     * @param locationId the ID of the location to update.
     * @param locationDetails the new details of the location.
     * @return the updated {@code Location} entity.
     * @throws ResourceNotFoundException if the location with the specified ID is not found.
     */
    Location updateLocation(int locationId, Location locationDetails) throws ResourceNotFoundException;

    /**
     * Deletes a location entity by its unique identifier.
     * 
     * @param locationId the ID of the location to delete.
     * @throws ResourceNotFoundException if the location with the specified ID is not found.
     */
    void deleteLocation(int locationId) throws ResourceNotFoundException;
}
