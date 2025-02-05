package es.iespuerto.mr.flora.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Plant;
import es.iespuerto.mr.flora.repository.PlantRepository;

public class PlantServiceTest {

    @Mock
    private PlantRepository plantRepository;

    @InjectMocks
    private PlantService plantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPlantsReturnsAllPlants() {
        Plant plant1 = new Plant();
        plant1.setCommonName("Plant 1");
        Plant plant2 = new Plant();
        plant2.setCommonName("Plant 2");

        when(plantRepository.findAll()).thenReturn(Arrays.asList(plant1, plant2));

        List<Plant> plants = plantService.getAllPlants();

        assertEquals(2, plants.size());
        assertEquals("Plant 1", plants.get(0).getCommonName());
        assertEquals("Plant 2", plants.get(1).getCommonName());
    }

    @Test
    void getPlantByIdReturnsPlantIfExists() throws ResourceNotFoundException {
        Plant plant = new Plant();
        plant.setId(1);
        plant.setCommonName("Test Plant");

        when(plantRepository.findById(1)).thenReturn(Optional.of(plant));

        Plant foundPlant = plantService.getPlantById(1);

        assertEquals(1, foundPlant.getId());
        assertEquals("Test Plant", foundPlant.getCommonName());
    }

    @Test
    void getPlantByIdThrowsExceptionIfPlantDoesNotExist() {
        when(plantRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            plantService.getPlantById(1);
        });
    }

    @Test
    void createPlantSavesAndReturnsPlant() {
        Plant plant = new Plant();
        plant.setCommonName("New Plant");

        when(plantRepository.save(plant)).thenReturn(plant);

        Plant createdPlant = plantService.createPlant(plant);

        assertEquals("New Plant", createdPlant.getCommonName());
    }

    @Test
    void updatePlantUpdatesAndReturnsPlant() throws ResourceNotFoundException {
        Plant existingPlant = new Plant();
        existingPlant.setId(1);
        existingPlant.setCommonName("Existing Plant");

        Plant updatedDetails = new Plant();
        updatedDetails.setCommonName("Updated Plant");

        when(plantRepository.findById(1)).thenReturn(Optional.of(existingPlant));
        when(plantRepository.save(existingPlant)).thenReturn(existingPlant);

        Plant updatedPlant = plantService.updatePlant(1, updatedDetails);

        assertEquals("Updated Plant", updatedPlant.getCommonName());
    }

    @Test
    void updatePlantThrowsExceptionIfPlantDoesNotExist() {
        Plant updatedDetails = new Plant();
        updatedDetails.setCommonName("Updated Plant");

        when(plantRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            plantService.updatePlant(1, updatedDetails);
        });
    }

    @Test
    void deletePlantDeletesPlantIfExists() throws ResourceNotFoundException {
        Plant plant = new Plant();
        plant.setId(1);
        plant.setCommonName("Plant to Delete");

        when(plantRepository.findById(1)).thenReturn(Optional.of(plant));
        doNothing().when(plantRepository).delete(plant);

        plantService.deletePlant(1);

        verify(plantRepository, times(1)).delete(plant);
    }

    @Test
    void deletePlantThrowsExceptionIfPlantDoesNotExist() {
        when(plantRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            plantService.deletePlant(1);
        });
    }
}