package es.iespuerto.mr.flora.soap.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Plant;
import es.iespuerto.mr.flora.service.PlantServiceInterface;
import jakarta.xml.ws.WebServiceException;

public class PlantServiceSoapImplTest {

    @Mock
    private PlantServiceInterface plantService;

    @InjectMocks
    private PlantServiceSoapImpl plantServiceSoap;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPlantsReturnsAllPlants() {
        Plant plant1 = new Plant();
        plant1.setId(1);
        Plant plant2 = new Plant();
        plant2.setId(2);

        when(plantService.getAllPlants()).thenReturn(Arrays.asList(plant1, plant2));

        List<Plant> plants = plantServiceSoap.getAllPlants();

        assertEquals(2, plants.size());
        assertEquals(1, plants.get(0).getId());
        assertEquals(2, plants.get(1).getId());
    }



    @Test
    void getPlantByIdReturnsPlantIfExists() throws ResourceNotFoundException {
        Plant plant = new Plant();
        plant.setId(1);

        when(plantService.getPlantById(1)).thenReturn(plant);

        Plant foundPlant = plantServiceSoap.getPlantById(1);

        assertEquals(1, foundPlant.getId());
    }

    @Test
    void getPlantByIdThrowsWebServiceExceptionIfPlantNotFound() throws ResourceNotFoundException {
        when(plantService.getPlantById(1)).thenThrow(new ResourceNotFoundException("Plant not found"));

        assertThrows(WebServiceException.class, () -> {
            plantServiceSoap.getPlantById(1);
        });
    }

    @Test
    void createPlantSavesAndReturnsPlant() {
        Plant plant = new Plant();
        plant.setId(1);

        when(plantService.createPlant(plant)).thenReturn(plant);

        Plant createdPlant = plantServiceSoap.createPlant(plant);

        assertEquals(1, createdPlant.getId());
    }

    @Test
    void createPlantThrowsWebServiceExceptionOnError() {
        Plant plant = new Plant();
        plant.setId(1);

        when(plantService.createPlant(plant)).thenThrow(new RuntimeException("Error"));

        assertThrows(WebServiceException.class, () -> {
            plantServiceSoap.createPlant(plant);
        });
    }

    @Test
    void updatePlantUpdatesAndReturnsPlant() throws ResourceNotFoundException {
        Plant existingPlant = new Plant();
        existingPlant.setId(1);

        Plant updatedDetails = new Plant();
        updatedDetails.setId(1);

        when(plantService.updatePlant(1, updatedDetails)).thenReturn(existingPlant);

        Plant updatedPlant = plantServiceSoap.updatePlant(1, updatedDetails);

        assertEquals(1, updatedPlant.getId());
    }

    @Test
    void updatePlantThrowsWebServiceExceptionIfPlantNotFound() throws ResourceNotFoundException {
        Plant updatedDetails = new Plant();
        updatedDetails.setId(1);

        when(plantService.updatePlant(1, updatedDetails)).thenThrow(new ResourceNotFoundException("Plant not found"));

        assertThrows(WebServiceException.class, () -> {
            plantServiceSoap.updatePlant(1, updatedDetails);
        });
    }

    @Test
    void deletePlantDeletesPlantIfExists() throws ResourceNotFoundException {
        doNothing().when(plantService).deletePlant(1);

        plantServiceSoap.deletePlant(1);

        verify(plantService, times(1)).deletePlant(1);
    }

    @Test
    void deletePlantThrowsWebServiceExceptionIfPlantNotFound() throws ResourceNotFoundException {
        doThrow(new ResourceNotFoundException("Plant not found")).when(plantService).deletePlant(1);

        assertThrows(WebServiceException.class, () -> {
            plantServiceSoap.deletePlant(1);
        });
    }
}