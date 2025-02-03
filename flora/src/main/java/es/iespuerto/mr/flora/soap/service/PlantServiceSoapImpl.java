package es.iespuerto.mr.flora.soap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.iespuerto.mr.flora.model.Plant;
import es.iespuerto.mr.flora.service.PlantServiceInterface;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;

/**
 * Implementation of the {@link PlantServiceSoapInterface} for SOAP web services.
 * This class provides methods to perform CRUD operations on {@link Plant} entities.
 * It delegates the actual business logic to the {@link PlantServiceInterface}.
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@link WebService}: Marks this class as a SOAP web service endpoint and specifies the 
 *       interface {@link PlantServiceSoapInterface} as the endpoint.</li>
 *   <li>{@link Autowired}: Injects the dependency of {@link PlantServiceInterface} to handle the 
 *       business logic for plant operations.</li>
 * </ul>
 *
 * <p>Methods provided by this service:</p>
 * <ul>
 *   <li>{@link #getAllPlants()}: Retrieves a list of all plants.</li>
 *   <li>{@link #getPlantById(int)}: Retrieves a plant by its ID.</li>
 *   <li>{@link #createPlant(Plant)}: Creates a new plant.</li>
 *   <li>{@link #updatePlant(int, Plant)}: Updates an existing plant by its ID.</li>
 *   <li>{@link #deletePlant(int)}: Deletes a plant by its ID.</li>
 * </ul>
 *
 * <p>Exceptions:</p>
 * <ul>
 *   <li>{@link WebServiceException}: Thrown when an error occurs during the execution of any 
 *       of the methods in this service. The exception is caught and rethrown with a specific 
 *       error message to indicate the issue.</li>
 * </ul>
 *
 * <p>Dependencies:</p>
 * <ul>
 *   <li>{@link PlantServiceInterface}: Provides the actual CRUD operations for plant entities.</li>
 * </ul>
 *
 * @see PlantServiceSoapInterface
 * @see PlantServiceInterface
 * @see WebServiceException
 */
@WebService(endpointInterface = "es.iespuerto.mr.flora.soap.service.PlantServiceSoapInterface")
public class PlantServiceSoapImpl implements PlantServiceSoapInterface {

    private PlantServiceInterface plantService;

    /**
     * Sets the {@link PlantServiceInterface} dependency.
     * 
     * @param plantService The {@link PlantServiceInterface} instance to be injected.
     */
    @Autowired
    public void setPlantService(PlantServiceInterface plantService) {
        this.plantService = plantService;
    }

    /**
     * Retrieves a list of all plants.
     * 
     * @return A list of all {@link Plant} entities.
     */
    @Override
    public List<Plant> getAllPlants() {
        return plantService.getAllPlants();
    }

    /**
     * Retrieves a plant by its ID.
     * 
     * @param plantId The ID of the plant to retrieve.
     * @return The {@link Plant} entity corresponding to the given ID.
     * @throws WebServiceException If an error occurs while retrieving the plant.
     */
    @Override
    public Plant getPlantById(int plantId) {
        try {
            return plantService.getPlantById(plantId);
        } catch (Exception e) {
            throw new WebServiceException("Error obteniendo la planta", e);
        }
    }

    /**
     * Creates a new plant.
     * 
     * @param plant The {@link Plant} entity to create.
     * @return The created {@link Plant} entity.
     * @throws WebServiceException If an error occurs while creating the plant.
     */
    @Override
    public Plant createPlant(Plant plant) {
        try {
            return plantService.createPlant(plant);
        } catch (Exception e) {
            throw new WebServiceException("Error creando la planta", e);
        }
    }

    /**
     * Updates an existing plant by its ID.
     * 
     * @param plantId The ID of the plant to update.
     * @param plantDetails The new details of the plant to update.
     * @return The updated {@link Plant} entity.
     * @throws WebServiceException If an error occurs while updating the plant.
     */
    @Override
    public Plant updatePlant(int plantId, Plant plantDetails) {
        try {
            return plantService.updatePlant(plantId, plantDetails);
        } catch (Exception e) {
            throw new WebServiceException("Error actualizando la planta", e);
        }
    }

    /**
     * Deletes a plant by its ID.
     * 
     * @param plantId The ID of the plant to delete.
     * @throws WebServiceException If an error occurs while deleting the plant.
     */
    @Override
    public void deletePlant(int plantId) {
        try {
            plantService.deletePlant(plantId);
        } catch (Exception e) {
            throw new WebServiceException("Error eliminando la planta", e);
        }
    }
}
