package es.iespuerto.mr.flora.service;

import java.util.List;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Plant;

/**
 * Interface for operations related to {@link Plant} entities.
 * This interface provides methods for performing CRUD operations on {@link Plant} objects.
 * The actual implementation of these methods should handle the persistence logic.
 * 
 * <p>Methods in this interface are designed to allow clients to:</p>
 * <ul>
 *   <li>Retrieve all plants.</li>
 *   <li>Retrieve a specific plant by its ID.</li>
 *   <li>Create a new plant.</li>
 *   <li>Update an existing plant by its ID.</li>
 *   <li>Delete a plant by its ID.</li>
 * </ul>
 * 
 * <p>Exceptions:</p>
 * <ul>
 *   <li>{@link ResourceNotFoundException} is thrown when a plant is not found during retrieval or update operations.</li>
 * </ul>
 * 
 * @see Plant
 * @see ResourceNotFoundException
 */
public interface PlantServiceInterface {

    /**
     * Retrieves all {@link Plant} entities.
     * 
     * @return a list of all {@link Plant} entities.
     */
    List<Plant> getAllPlants();

    /**
     * Retrieves a specific {@link Plant} by its ID.
     * 
     * @param plantId the ID of the plant to retrieve.
     * @return the {@link Plant} entity with the specified ID.
     * @throws ResourceNotFoundException if no plant is found with the given ID.
     */
    Plant getPlantById(int plantId) throws ResourceNotFoundException;

    /**
     * Creates a new {@link Plant} entity.
     * 
     * @param plant the {@link Plant} entity to create.
     * @return the created {@link Plant} entity.
     */
    Plant createPlant(Plant plant);

    /**
     * Updates an existing {@link Plant} entity.
     * 
     * @param plantId the ID of the plant to update.
     * @param plantDetails the updated {@link Plant} details.
     * @return the updated {@link Plant} entity.
     * @throws ResourceNotFoundException if no plant is found with the given ID.
     */
    Plant updatePlant(int plantId, Plant plantDetails) throws ResourceNotFoundException;

    /**
     * Deletes a {@link Plant} entity by its ID.
     * 
     * @param plantId the ID of the plant to delete.
     * @throws ResourceNotFoundException if no plant is found with the given ID.
     */
    void deletePlant(int plantId) throws ResourceNotFoundException;
}
