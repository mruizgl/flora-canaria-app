package es.iespuerto.mr.flora.controller;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Characteristic;
import es.iespuerto.mr.flora.service.CharacteristicServiceInterface;
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
public class CharacteristicsControllerTest {

    @Mock
    private CharacteristicServiceInterface characteristicService;

    @InjectMocks
    private CharacteristicsController characteristicsController;

    private Characteristic characteristic;

    @BeforeEach
    void setUp() {
        characteristic = new Characteristic();
        characteristic.setId(1);
        characteristic.setDescripcion("Test Characteristic");
    }

    @Test
    void getAllCharacteristicsReturnsListOfCharacteristics() {
        when(characteristicService.getAllCharacteristics()).thenReturn(List.of(characteristic));

        List<Characteristic> characteristics = characteristicsController.getAllCharacteristics();

        assertNotNull(characteristics);
        assertEquals(1, characteristics.size());
        assertEquals("Test Characteristic", characteristics.get(0).getDescripcion());
    }

    @Test
    void getCharacteristicByIdReturnsCharacteristic() throws ResourceNotFoundException {
        when(characteristicService.getCharacteristicById(1)).thenReturn(characteristic);

        ResponseEntity<Characteristic> response = characteristicsController.getCharacteristicById(1);

        assertNotNull(response.getBody());
        assertEquals("Test Characteristic", response.getBody().getDescripcion());
    }

    @Test
    void getCharacteristicByIdThrowsResourceNotFoundException() throws ResourceNotFoundException {
        when(characteristicService.getCharacteristicById(1)).thenThrow(new ResourceNotFoundException("Characteristic not found"));

        assertThrows(ResourceNotFoundException.class, () -> characteristicsController.getCharacteristicById(1));
    }

    @Test
    void createCharacteristicReturnsCreatedCharacteristic() {
        when(characteristicService.createCharacteristic(any(Characteristic.class))).thenReturn(characteristic);

        Characteristic createdCharacteristic = characteristicsController.createCharacteristic(characteristic);

        assertNotNull(createdCharacteristic);
        assertEquals("Test Characteristic", createdCharacteristic.getDescripcion());
    }

    @Test
    void updateCharacteristicReturnsUpdatedCharacteristic() throws ResourceNotFoundException {
        when(characteristicService.updateCharacteristic(eq(1), any(Characteristic.class))).thenReturn(characteristic);

        ResponseEntity<Characteristic> response = characteristicsController.updateCharacteristic(1, characteristic);

        assertNotNull(response.getBody());
        assertEquals("Test Characteristic", response.getBody().getDescripcion());
    }

    @Test
    void updateCharacteristicThrowsResourceNotFoundException() throws ResourceNotFoundException {
        when(characteristicService.updateCharacteristic(eq(1), any(Characteristic.class))).thenThrow(new ResourceNotFoundException("Characteristic not found"));

        assertThrows(ResourceNotFoundException.class, () -> characteristicsController.updateCharacteristic(1, characteristic));
    }

    @Test
    void deleteCharacteristicReturnsSuccessMap() throws ResourceNotFoundException {
        doNothing().when(characteristicService).deleteCharacteristic(1);

        Map<String, Boolean> response = characteristicsController.deleteCharacteristic(1);

        assertTrue(response.get("deleted"));
    }

    @Test
    void deleteCharacteristicThrowsResourceNotFoundException() throws ResourceNotFoundException {
        doThrow(new ResourceNotFoundException("Characteristic not found")).when(characteristicService).deleteCharacteristic(1);

        assertThrows(ResourceNotFoundException.class, () -> characteristicsController.deleteCharacteristic(1));
    }
}