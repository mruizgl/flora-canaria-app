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
import es.iespuerto.mr.flora.model.Characteristic;
import es.iespuerto.mr.flora.service.CharacteristicServiceInterface;
import jakarta.xml.ws.WebServiceException;

public class CharacteristicServiceSoapImplTest {

    @Mock
    private CharacteristicServiceInterface characteristicService;

    @InjectMocks
    private CharacteristicServiceSoapImpl characteristicServiceSoap;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCharacteristicsReturnsAllCharacteristics() {
        Characteristic characteristic1 = new Characteristic();
        characteristic1.setDescripcion("Characteristic 1");
        Characteristic characteristic2 = new Characteristic();
        characteristic2.setDescripcion("Characteristic 2");

        when(characteristicService.getAllCharacteristics()).thenReturn(Arrays.asList(characteristic1, characteristic2));

        List<Characteristic> characteristics = characteristicServiceSoap.getAllCharacteristics();

        assertEquals(2, characteristics.size());
        assertEquals("Characteristic 1", characteristics.get(0).getDescripcion());
        assertEquals("Characteristic 2", characteristics.get(1).getDescripcion());
    }



    @Test
    void getCharacteristicByIdReturnsCharacteristicIfExists() throws ResourceNotFoundException {
        Characteristic characteristic = new Characteristic();
        characteristic.setId(1);
        characteristic.setDescripcion("Test Characteristic");

        when(characteristicService.getCharacteristicById(1)).thenReturn(characteristic);

        Characteristic foundCharacteristic = characteristicServiceSoap.getCharacteristicById(1);

        assertEquals(1, foundCharacteristic.getId());
        assertEquals("Test Characteristic", foundCharacteristic.getDescripcion());
    }

    @Test
    void getCharacteristicByIdThrowsWebServiceExceptionIfCharacteristicNotFound() throws ResourceNotFoundException {
        when(characteristicService.getCharacteristicById(1)).thenThrow(new ResourceNotFoundException("Characteristic not found"));

        assertThrows(WebServiceException.class, () -> {
            characteristicServiceSoap.getCharacteristicById(1);
        });
    }

    @Test
    void createCharacteristicSavesAndReturnsCharacteristic() {
        Characteristic characteristic = new Characteristic();
        characteristic.setDescripcion("New Characteristic");

        when(characteristicService.createCharacteristic(characteristic)).thenReturn(characteristic);

        Characteristic createdCharacteristic = characteristicServiceSoap.createCharacteristic(characteristic);

        assertEquals("New Characteristic", createdCharacteristic.getDescripcion());
    }

    @Test
    void createCharacteristicThrowsWebServiceExceptionOnError() {
        Characteristic characteristic = new Characteristic();
        characteristic.setDescripcion("New Characteristic");

        when(characteristicService.createCharacteristic(characteristic)).thenThrow(new RuntimeException("Error"));

        assertThrows(WebServiceException.class, () -> {
            characteristicServiceSoap.createCharacteristic(characteristic);
        });
    }



    @Test
    void updateCharacteristicThrowsWebServiceExceptionIfCharacteristicNotFound() throws ResourceNotFoundException {
        Characteristic updatedDetails = new Characteristic();
        updatedDetails.setDescripcion("Updated Characteristic");

        when(characteristicService.updateCharacteristic(1, updatedDetails)).thenThrow(new ResourceNotFoundException("Characteristic not found"));

        assertThrows(WebServiceException.class, () -> {
            characteristicServiceSoap.updateCharacteristic(1, updatedDetails);
        });
    }

    @Test
    void deleteCharacteristicDeletesCharacteristicIfExists() throws ResourceNotFoundException {
        doNothing().when(characteristicService).deleteCharacteristic(1);

        characteristicServiceSoap.deleteCharacteristic(1);

        verify(characteristicService, times(1)).deleteCharacteristic(1);
    }

    @Test
    void deleteCharacteristicThrowsWebServiceExceptionIfCharacteristicNotFound() throws ResourceNotFoundException {
        doThrow(new ResourceNotFoundException("Characteristic not found")).when(characteristicService).deleteCharacteristic(1);

        assertThrows(WebServiceException.class, () -> {
            characteristicServiceSoap.deleteCharacteristic(1);
        });
    }
}