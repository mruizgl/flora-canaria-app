package es.iespuerto.mr.flora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Location;
import es.iespuerto.mr.flora.repository.LocationRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * Service class for managing locations.
 * This class provides methods to perform CRUD (Create, Read, Update, Delete) operations 
 * on {@link Location} entities. It implements the {@link LocationServiceInterface}.
 * 
 * <p>This service is used to interact with the {@link LocationRepository} and manage the 
 * persistence of location data.</p>
 * 
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Slf4j} - Lombok annotation for logging. Provides a logger for the class.</li>
 *   <li>{@code @Component} - Indicates that this class is a Spring component, enabling 
 *       Spring's dependency injection to automatically wire the service.</li>
 *   <li>{@code @Autowired} - Marks a constructor, field, setter method, or configuration 
 *       method to be autowired by Spring's dependency injection mechanism.</li>
 *   <li>{@code @PathVariable} - Used to bind method parameters to URI template variables in the controller.</li>
 *   <li>{@code @Valid} - Ensures that method parameters are validated according to the constraints.</li>
 *   <li>{@code @RequestBody} - Indicates that a method parameter should be bound to the body of the HTTP request.</li>
 * </ul>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #getAllLocations()} - Retrieves a list of all locations from the repository.</li>
 *   <li>{@link #getLocationById(int)} - Retrieves a specific location by its ID.</li>
 *   <li>{@link #createLocation(Location)} - Creates a new location and saves it to the repository.</li>
 *   <li>{@link #updateLocation(int, Location)} - Updates an existing location with new details.</li>
 *   <li>{@link #deleteLocation(int)} - Deletes a location by its ID from the repository.</li>
 * </ul>
 * 
 * @author Melissa Ruiz
 */
@Slf4j
@Component
public class LocationService implements LocationServiceInterface {

    private LocationRepository locationRepository;

    /**
     * Sets the location repository to be used by the service.
     * 
     * @param locationRepository the {@link LocationRepository} to be injected by Spring.
     */
    @Autowired
    public void setLocationRepository(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    /**
     * Retrieves all locations from the repository.
     * 
     * @return a list of all {@link Location} entities.
     */
    @Override
    public List<Location> getAllLocations() {
        log.info("Realizando la llamada al servicio");
        return locationRepository.findAll();
    }

    /**
     * Retrieves a specific location by its ID.
     * 
     * @param locationId the ID of the location to retrieve.
     * @return the {@link Location} entity.
     * @throws ResourceNotFoundException if the location with the specified ID is not found.
     */
    @Override
    public Location getLocationById(@PathVariable(value = "id") int locationId) throws ResourceNotFoundException {
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found for this id :: " + locationId));
    }

    /**
     * Creates a new location and saves it to the repository.
     * 
     * @param location the {@link Location} entity to be created.
     * @return the created {@link Location} entity.
     */
    @Override
    public Location createLocation(@Valid @RequestBody Location location) {
        return locationRepository.save(location);
    }

    /**
     * Updates an existing location with new details.
     * 
     * @param locationId the ID of the location to update.
     * @param locationDetails the new details for the location.
     * @return the updated {@link Location} entity.
     * @throws ResourceNotFoundException if the location with the specified ID is not found.
     */
    @Override
    public Location updateLocation(@PathVariable(value = "id") int locationId,
                                   @Valid @RequestBody Location locationDetails) throws ResourceNotFoundException {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found for this id :: " + locationId));

        location.setName(locationDetails.getName());
        return locationRepository.save(location);
    }

    /**
     * Deletes a location by its ID.
     * 
     * @param locationId the ID of the location to delete.
     * @throws ResourceNotFoundException if the location with the specified ID is not found.
     */
    @Override
    public void deleteLocation(@PathVariable(value = "id") int locationId) throws ResourceNotFoundException {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found for this id :: " + locationId));

        locationRepository.delete(location);
    }
}
