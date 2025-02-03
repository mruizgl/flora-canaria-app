package es.iespuerto.mr.flora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Characteristic;
import es.iespuerto.mr.flora.repository.CharacteristicRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * Service class for managing characteristics.
 * This class provides methods to perform CRUD operations on characteristics.
 * It implements the {@link CharacteristicServiceInterface}.
 * 
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@link Slf4j} - For logging purposes. This annotation enables logging in the class.</li>
 *   <li>{@link Component} - Indicates that this class is a Spring component that will be managed by Spring's container.</li>
 *   <li>{@link Autowired} - Used to inject the {@link CharacteristicRepository} dependency into the service.</li>
 * </ul>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #getAllCharacteristics()} - Retrieves all characteristics from the repository.</li>
 *   <li>{@link #getCharacteristicById(int)} - Retrieves a characteristic by its ID from the repository.</li>
 *   <li>{@link #createCharacteristic(Characteristic)} - Creates a new characteristic and saves it to the repository.</li>
 *   <li>{@link #updateCharacteristic(int, Characteristic)} - Updates an existing characteristic by its ID.</li>
 *   <li>{@link #deleteCharacteristic(int)} - Deletes a characteristic by its ID from the repository.</li>
 * </ul>
 * 
 * <p>Exceptions:</p>
 * <ul>
 *   <li>{@link ResourceNotFoundException} - Thrown when a characteristic is not found by its ID.</li>
 * </ul>
 * 
 * @author Melissa Ruiz
 */
@Slf4j
@Component
public class CharacteristicService implements CharacteristicServiceInterface {

    private CharacteristicRepository characteristicRepository;

    /**
     * Sets the {@link CharacteristicRepository} instance for the service.
     * 
     * @param characteristicRepository The repository to be injected.
     */
    @Autowired
    public void setCharacteristicRepository(CharacteristicRepository characteristicRepository) {
        this.characteristicRepository = characteristicRepository;
    }

    /**
     * Retrieves all characteristics from the repository.
     * 
     * @return A list of all characteristics.
     */
    @Override
    public List<Characteristic> getAllCharacteristics() {
        log.info("Realizando la llamada al servicio");
        return characteristicRepository.findAll();
    }

    /**
     * Retrieves a characteristic by its ID.
     * 
     * @param characteristicId The ID of the characteristic to retrieve.
     * @return The characteristic with the specified ID.
     * @throws ResourceNotFoundException If the characteristic is not found.
     */
    @Override
    public Characteristic getCharacteristicById(@PathVariable(value = "id") int characteristicId) throws ResourceNotFoundException {
        return characteristicRepository.findById(characteristicId)
                .orElseThrow(() -> new ResourceNotFoundException("Characteristic not found for this id :: " + characteristicId));
    }

    /**
     * Creates a new characteristic and saves it to the repository.
     * 
     * @param characteristic The characteristic to create.
     * @return The newly created characteristic.
     */
    @Override
    public Characteristic createCharacteristic(@Valid @RequestBody Characteristic characteristic) {
        return characteristicRepository.save(characteristic);
    }

    /**
     * Updates an existing characteristic by its ID.
     * 
     * @param characteristicId The ID of the characteristic to update.
     * @param characteristicDetails The new details for the characteristic.
     * @return The updated characteristic.
     * @throws ResourceNotFoundException If the characteristic with the specified ID is not found.
     */
    @Override
    public Characteristic updateCharacteristic(@PathVariable(value = "id") int characteristicId,
                                               @Valid @RequestBody Characteristic characteristicDetails) throws ResourceNotFoundException {
        Characteristic characteristic = characteristicRepository.findById(characteristicId)
                .orElseThrow(() -> new ResourceNotFoundException("Characteristic not found for this id :: " + characteristicId));

        characteristic.setDescripcion(characteristicDetails.getDescripcion());
        return characteristicRepository.save(characteristic);
    }

    /**
     * Deletes a characteristic by its ID.
     * 
     * @param characteristicId The ID of the characteristic to delete.
     * @throws ResourceNotFoundException If the characteristic with the specified ID is not found.
     */
    @Override
    public void deleteCharacteristic(@PathVariable(value = "id") int characteristicId) throws ResourceNotFoundException {
        Characteristic characteristic = characteristicRepository.findById(characteristicId)
                .orElseThrow(() -> new ResourceNotFoundException("Characteristic not found for this id :: " + characteristicId));

        characteristicRepository.delete(characteristic);
    }
}
