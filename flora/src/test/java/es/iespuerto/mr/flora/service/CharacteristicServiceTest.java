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
import es.iespuerto.mr.flora.model.Characteristic;
import es.iespuerto.mr.flora.repository.CharacteristicRepository;

public class CharacteristicServiceTest {

    @Mock
    private CharacteristicRepository characteristicRepository;

    @InjectMocks
    private CharacteristicService characteristicService;

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

        when(characteristicRepository.findAll()).thenReturn(Arrays.asList(characteristic1, characteristic2));

        List<Characteristic> characteristics = characteristicService.getAllCharacteristics();

        assertEquals(2, characteristics.size());
        assertEquals("Characteristic 1", characteristics.get(0).getDescripcion());
        assertEquals("Characteristic 2", characteristics.get(1).getDescripcion());
    }

    @Test
    void getCharacteristicByIdReturnsCharacteristicIfExists() throws ResourceNotFoundException {
        Characteristic characteristic = new Characteristic();
        characteristic.setId(1);
        characteristic.setDescripcion("Test Characteristic");

        when(characteristicRepository.findById(1)).thenReturn(Optional.of(characteristic));

        Characteristic foundCharacteristic = characteristicService.getCharacteristicById(1);

        assertEquals(1, foundCharacteristic.getId());
        assertEquals("Test Characteristic", foundCharacteristic.getDescripcion());
    }

    @Test
    void getCharacteristicByIdThrowsExceptionIfCharacteristicDoesNotExist() {
        when(characteristicRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            characteristicService.getCharacteristicById(1);
        });
    }

    @Test
    void createCharacteristicSavesAndReturnsCharacteristic() {
        Characteristic characteristic = new Characteristic();
        characteristic.setDescripcion("New Characteristic");

        when(characteristicRepository.save(characteristic)).thenReturn(characteristic);

        Characteristic createdCharacteristic = characteristicService.createCharacteristic(characteristic);

        assertEquals("New Characteristic", createdCharacteristic.getDescripcion());
    }

    @Test
    void updateCharacteristicUpdatesAndReturnsCharacteristic() throws ResourceNotFoundException {
        Characteristic existingCharacteristic = new Characteristic();
        existingCharacteristic.setId(1);
        existingCharacteristic.setDescripcion("Existing Characteristic");

        Characteristic updatedDetails = new Characteristic();
        updatedDetails.setDescripcion("Updated Characteristic");

        when(characteristicRepository.findById(1)).thenReturn(Optional.of(existingCharacteristic));
        when(characteristicRepository.save(existingCharacteristic)).thenReturn(existingCharacteristic);

        Characteristic updatedCharacteristic = characteristicService.updateCharacteristic(1, updatedDetails);

        assertEquals("Updated Characteristic", updatedCharacteristic.getDescripcion());
    }

    @Test
    void updateCharacteristicThrowsExceptionIfCharacteristicDoesNotExist() {
        Characteristic updatedDetails = new Characteristic();
        updatedDetails.setDescripcion("Updated Characteristic");

        when(characteristicRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            characteristicService.updateCharacteristic(1, updatedDetails);
        });
    }

    @Test
    void deleteCharacteristicDeletesCharacteristicIfExists() throws ResourceNotFoundException {
        Characteristic characteristic = new Characteristic();
        characteristic.setId(1);
        characteristic.setDescripcion("Characteristic to Delete");

        when(characteristicRepository.findById(1)).thenReturn(Optional.of(characteristic));
        doNothing().when(characteristicRepository).delete(characteristic);

        characteristicService.deleteCharacteristic(1);

        verify(characteristicRepository, times(1)).delete(characteristic);
    }

    @Test
    void deleteCharacteristicThrowsExceptionIfCharacteristicDoesNotExist() {
        when(characteristicRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            characteristicService.deleteCharacteristic(1);
        });
    }
}