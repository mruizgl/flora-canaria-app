package es.iespuerto.mr.flora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Plant;
import es.iespuerto.mr.flora.repository.PlantRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * Service class for managing {@link Plant} entities.
 * Provides CRUD operations for performing various actions on {@link Plant} objects.
 * This class is a Spring {@link Component} that acts as a service layer for handling plant-related business logic.
 * 
 * <p>Logging is enabled via {@link Slf4j}, which logs key actions, such as retrieving all plants.</p>
 * 
 * <p>Methods in this class include:</p>
 * <ul>
 *   <li>{@link #getAllPlants()}: Retrieves all plants.</li>
 *   <li>{@link #getPlantById(int)}: Retrieves a specific plant by its ID.</li>
 *   <li>{@link #createPlant(Plant)}: Creates a new plant entity.</li>
 *   <li>{@link #updatePlant(int, Plant)}: Updates an existing plant entity.</li>
 *   <li>{@link #deletePlant(int)}: Deletes a plant entity by its ID.</li>
 * </ul>
 * 
 * <p>Exceptions thrown:</p>
 * <ul>
 *   <li>{@link ResourceNotFoundException}: Thrown when a plant is not found based on the provided ID.</li>
 * </ul>
 * 
 * <p>Dependencies:</p>
 * <ul>
 *   <li>{@link PlantRepository}: Repository that interacts with the database to manage {@link Plant} entities.</li>
 * </ul>
 * 
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@link Autowired}: Injects the {@link PlantRepository} dependency.</li>
 *   <li>{@link PathVariable}: Used to extract the plant ID from the request URL.</li>
 *   <li>{@link RequestBody}: Used to extract the plant details from the request body.</li>
 *   <li>{@link Valid}: Used to ensure that the plant details are valid before processing the request.</li>
 * </ul>
 * 
 * <p>Logging:</p>
 * <ul>
 *   <li>Logs an info message each time the {@link #getAllPlants()} method is invoked.</li>
 * </ul>
 * 
 * @see PlantServiceInterface
 * @see PlantRepository
 * @see ResourceNotFoundException
 */
@Slf4j
@Component
public class PlantService implements PlantServiceInterface {

    private PlantRepository plantRepository;

    /**
     * Sets the {@link PlantRepository} dependency through {@link Autowired}.
     * 
     * @param plantRepository the {@link PlantRepository} to be injected.
     */
    @Autowired
    public void setPlantRepository(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    /**
     * Retrieves all {@link Plant} entities from the database.
     * 
     * @return a list of all {@link Plant} entities.
     */
    @Override
    public List<Plant> getAllPlants() {
        log.info("Realizando la llamada al servicio");
        return plantRepository.findAll();
    }

    /**
     * Retrieves a specific {@link Plant} by its ID.
     * 
     * @param plantId the ID of the plant to retrieve.
     * @return the {@link Plant} entity with the specified ID.
     * @throws ResourceNotFoundException if no plant is found with the given ID.
     */
    @Override
    public Plant getPlantById(int plantId) throws ResourceNotFoundException {
        return plantRepository.findById(plantId)
                .orElseThrow(() -> new ResourceNotFoundException("Plant not found for this id :: " + plantId));
    }

    /**
     * Creates a new {@link Plant} entity and saves it to the database.
     * 
     * @param plant the {@link Plant} entity to create.
     * @return the created {@link Plant} entity.
     */
    @Override
    public Plant createPlant(@PathVariable(value = "id") Plant plant) {
        return plantRepository.save(plant);
    }

    /**
     * Updates an existing {@link Plant} entity by its ID.
     * 
     * @param plantId the ID of the plant to update.
     * @param plantDetails the updated {@link Plant} details.
     * @return the updated {@link Plant} entity.
     * @throws ResourceNotFoundException if no plant is found with the given ID.
     */
    @Override
    public Plant updatePlant(@PathVariable(value = "id") int plantId, @Valid @RequestBody Plant plantDetails)
            throws ResourceNotFoundException {
        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> new ResourceNotFoundException("Plant not found for this id :: " + plantId));
        plant.setCommonName(plantDetails.getCommonName());
        plant.setScientificName(plantDetails.getScientificName());
        return plantRepository.save(plant);
    }

    /**
     * Deletes a {@link Plant} entity by its ID.
     * 
     * @param plantId the ID of the plant to delete.
     * @throws ResourceNotFoundException if no plant is found with the given ID.
     */
    @Override
    public void deletePlant(@PathVariable(value = "id") int plantId) throws ResourceNotFoundException {
        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> new ResourceNotFoundException("Plant not found for this id :: " + plantId));
        plantRepository.delete(plant);
    }
}
