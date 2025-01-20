package es.iespuerto.mr.flora.service;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Plant;
import es.iespuerto.mr.flora.repository.PlantRepository;
import org.apache.cxf.annotations.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Component
public class PlantService implements PlantServiceInterface {
    private PlantRepository plantRepository;

    @Autowired
    public void setPlantRepository(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }


    @Override
    public List<Plant> getAllPlants() {
        log.info("Realizando la llamada al servicio");
        return plantRepository.findAll();
    }

    @Override
    public Plant getPlantById(int plantId) throws ResourceNotFoundException {
        return plantRepository.findById(plantId).orElseThrow(() -> new ResourceNotFoundException("Plant not found for this id :: " + plantId));
    }

    @Override
    public Plant createPlant(@PathVariable(value = "id") Plant plant) {
        return plantRepository.save(plant);
    }

    @Override
    public Plant updatePlant(@PathVariable(value = "id") int plantId, @Valid @RequestBody Plant plantDetails) throws ResourceNotFoundException {
        Plant plant = plantRepository.findById(plantId).orElseThrow(() -> new ResourceNotFoundException("Plant not found for this id :: " + plantId));
        plant.setCommonName(plantDetails.getCommonName());
        plant.setScientificName(plantDetails.getScientificName());
        return plantRepository.save(plant);
    }

    @Override
    public void deletePlant(@PathVariable(value = "id") int plantId) throws ResourceNotFoundException {
        Plant plant = plantRepository.findById(plantId).orElseThrow(() -> new ResourceNotFoundException("Plant not found for this id :: " + plantId));
        plantRepository.delete(plant);
    }
}