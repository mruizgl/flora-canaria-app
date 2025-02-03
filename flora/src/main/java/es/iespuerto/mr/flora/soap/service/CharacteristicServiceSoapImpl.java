package es.iespuerto.mr.flora.soap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.iespuerto.mr.flora.model.Characteristic;
import es.iespuerto.mr.flora.service.CharacteristicServiceInterface;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;

/**
 * Implementation of the {@link CharacteristicServiceSoapInterface}.
 * This class provides SOAP web service methods for managing characteristics.
 * It delegates the actual operations to the {@link CharacteristicServiceInterface}.
 * 
 * <p>Methods in this class handle exceptions by throwing {@link WebServiceException}
 * with appropriate error messages if any issues occur during the execution of a web service method.</p>
 * 
 * <p>Annotations:</p>
 * <ul>
 *   <li>{@code @WebService}: Specifies the endpoint interface for the SOAP web service.</li>
 *   <li>{@code @Autowired}: Indicates that the setter method for the characteristic service should be autowired by Spring.</li>
 * </ul>
 * 
 * <p>Methods:</p>
 * <ul>
 *   <li>{@link #getAllCharacteristics()}: Retrieves all characteristics.</li>
 *   <li>{@link #getCharacteristicById(int)}: Retrieves a characteristic by its ID.</li>
 *   <li>{@link #createCharacteristic(Characteristic)}: Creates a new characteristic.</li>
 *   <li>{@link #updateCharacteristic(int, Characteristic)}: Updates an existing characteristic.</li>
 *   <li>{@link #deleteCharacteristic(int)}: Deletes a characteristic by its ID.</li>
 * </ul>
 * 
 * <p>Exceptions:</p>
 * <ul>
 *   <li>{@link WebServiceException}: Thrown when an error occurs during the execution of a web service method.</li>
 * </ul>
 * 
 * @see CharacteristicServiceSoapInterface
 * @see CharacteristicServiceInterface
 * 
 */
@WebService(endpointInterface = "es.iespuerto.mr.flora.soap.service.CharacteristicServiceSoapInterface")
public class CharacteristicServiceSoapImpl implements CharacteristicServiceSoapInterface {

    private CharacteristicServiceInterface characteristicService;

    /**
     * Sets the {@link CharacteristicServiceInterface} instance for the SOAP service.
     * This method is used by Spring's dependency injection.
     * 
     * @param characteristicService The service to be injected.
     */
    @Autowired
    public void setCharacteristicService(CharacteristicServiceInterface characteristicService) {
        this.characteristicService = characteristicService;
    }

    /**
     * Retrieves all characteristics by delegating the request to the characteristic service.
     * 
     * @return A list of all characteristics.
     */
    @Override
    public List<Characteristic> getAllCharacteristics() {
        return characteristicService.getAllCharacteristics();
    }

    /**
     * Retrieves a characteristic by its ID from the characteristic service.
     * If the characteristic is not found, a {@link WebServiceException} will be thrown.
     * 
     * @param characteristicId The ID of the characteristic to retrieve.
     * @return The characteristic with the specified ID.
     * @throws WebServiceException If an error occurs while retrieving the characteristic.
     */
    @Override
    public Characteristic getCharacteristicById(int characteristicId) {
        try {
            return characteristicService.getCharacteristicById(characteristicId);
        } catch (Exception e) {
            throw new WebServiceException("Error obteniendo la característica", e);
        }
    }

    /**
     * Creates a new characteristic by delegating the request to the characteristic service.
     * If the characteristic cannot be created, a {@link WebServiceException} will be thrown.
     * 
     * @param characteristic The characteristic to create.
     * @return The newly created characteristic.
     * @throws WebServiceException If an error occurs while creating the characteristic.
     */
    @Override
    public Characteristic createCharacteristic(Characteristic characteristic) {
        try {
            return characteristicService.createCharacteristic(characteristic);
        } catch (Exception e) {
            throw new WebServiceException("Error creando la característica", e);
        }
    }

    /**
     * Updates an existing characteristic by its ID and the new characteristic details.
     * If the characteristic cannot be updated, a {@link WebServiceException} will be thrown.
     * 
     * @param characteristicId The ID of the characteristic to update.
     * @param characteristicDetails The new details for the characteristic.
     * @return The updated characteristic.
     * @throws WebServiceException If an error occurs while updating the characteristic.
     */
    @Override
    public Characteristic updateCharacteristic(int characteristicId, Characteristic characteristicDetails) {
        try {
            return characteristicService.updateCharacteristic(characteristicId, characteristicDetails);
        } catch (Exception e) {
            throw new WebServiceException("Error actualizando la característica", e);
        }
    }

    /**
     * Deletes a characteristic by its ID.
     * If the characteristic cannot be deleted, a {@link WebServiceException} will be thrown.
     * 
     * @param characteristicId The ID of the characteristic to delete.
     * @throws WebServiceException If an error occurs while deleting the characteristic.
     */
    @Override
    public void deleteCharacteristic(int characteristicId) {
        try {
            characteristicService.deleteCharacteristic(characteristicId);
        } catch (Exception e) {
            throw new WebServiceException("Error eliminando la característica", e);
        }
    }
}
