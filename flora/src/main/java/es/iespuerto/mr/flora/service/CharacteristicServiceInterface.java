package es.iespuerto.mr.flora.service;

import java.util.List;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Characteristic;

/**
 * Interface for managing characteristics.
 */
public interface CharacteristicServiceInterface {

    /**
     * Retrieves all characteristics.
     *
     * @return a list of all characteristics.
     */
    List<Characteristic> getAllCharacteristics();

    /**
     * Retrieves a characteristic by its ID.
     *
     * @param characteristicId the ID of the characteristic to retrieve.
     * @return the characteristic with the specified ID.
     * @throws ResourceNotFoundException if the characteristic with the specified ID is not found.
     */
    Characteristic getCharacteristicById(int characteristicId) throws ResourceNotFoundException;

    /**
     * Creates a new characteristic.
     *
     * @param characteristic the characteristic to create.
     * @return the created characteristic.
     */
    Characteristic createCharacteristic(Characteristic characteristic);

    /**
     * Updates an existing characteristic.
     *
     * @param characteristicId the ID of the characteristic to update.
     * @param characteristicDetails the details to update the characteristic with.
     * @return the updated characteristic.
     * @throws ResourceNotFoundException if the characteristic with the specified ID is not found.
     */
    Characteristic updateCharacteristic(int characteristicId, Characteristic characteristicDetails) throws ResourceNotFoundException;

    /**
     * Deletes a characteristic by its ID.
     *
     * @param characteristicId the ID of the characteristic to delete.
     * @throws ResourceNotFoundException if the characteristic with the specified ID is not found.
     */
    void deleteCharacteristic(int characteristicId) throws ResourceNotFoundException;
}