package es.iespuerto.mr.flora.controller;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Plant;
import es.iespuerto.mr.flora.service.PlantServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlantsControllerTest {

    @Mock
    private PlantServiceInterface plantService;

    @InjectMocks
    private PlantsController plantsController;

    private Plant plant;

    @BeforeEach
    void setUp() {
        plant = new Plant();
        plant.setId(1);
        plant.setCommonName("Test Plant");
    }

    @Test
    void getAllPlantsReturnsListOfPlants() {
        when(plantService.getAllPlants()).thenReturn(List.of(plant));

        List<Plant> plants = plantsController.getAllPlants();

        assertNotNull(plants);
        assertEquals(1, plants.size());
        assertEquals("Test Plant", plants.get(0).getCommonName());
    }

    @Test
    void getPlantByIdReturnsPlant() throws ResourceNotFoundException {
        when(plantService.getPlantById(1)).thenReturn(plant);

        ResponseEntity<Plant> response = plantsController.getPlantById(1);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test Plant", response.getBody().getCommonName());
    }

    @Test
    void getPlantByIdThrowsResourceNotFoundException() throws ResourceNotFoundException {
        when(plantService.getPlantById(1)).thenThrow(new ResourceNotFoundException("Plant not found"));

        assertThrows(ResourceNotFoundException.class, () -> plantsController.getPlantById(1));
    }

    @Test
    void createPlantReturnsCreatedPlant() {
        when(plantService.createPlant(any(Plant.class))).thenReturn(plant);

        Plant createdPlant = plantsController.createPlant(plant);

        assertNotNull(createdPlant);
        assertEquals("Test Plant", createdPlant.getCommonName());
    }

    @Test
    void updatePlantReturnsUpdatedPlant() throws ResourceNotFoundException {
        when(plantService.updatePlant(eq(1), any(Plant.class))).thenReturn(plant);

        ResponseEntity<Plant> response = plantsController.updatePlant(1, plant);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test Plant", response.getBody().getCommonName());
    }

    @Test
    void updatePlantThrowsResourceNotFoundException() throws ResourceNotFoundException {
        when(plantService.updatePlant(eq(1), any(Plant.class))).thenThrow(new ResourceNotFoundException("Plant not found"));

        assertThrows(ResourceNotFoundException.class, () -> plantsController.updatePlant(1, plant));
    }

    @Test
    void deletePlantReturnsSuccessMap() throws ResourceNotFoundException {
        doNothing().when(plantService).deletePlant(1);

        Map<String, Boolean> response = plantsController.deletePlant(1);

        assertTrue(response.get("deleted"));
    }

    @Test
    void deletePlantThrowsResourceNotFoundException() throws ResourceNotFoundException {
        doThrow(new ResourceNotFoundException("Plant not found")).when(plantService).deletePlant(1);

        assertThrows(ResourceNotFoundException.class, () -> plantsController.deletePlant(1));
    }
}