package es.iespuerto.mr.flora_app.service;

import es.iespuerto.mr.flora_app.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora_app.model.Plant;

import java.util.List;

public interface PlantServiceInterface {
    List<Plant> getAllPlants();
    Plant getPlantById(int plantId) throws ResourceNotFoundException;
    Plant createPlant(Plant plant);
    Plant updatePlant(int plantId, Plant plantDetails) throws ResourceNotFoundException;
    void deletePlant(int plantId) throws ResourceNotFoundException;
}
