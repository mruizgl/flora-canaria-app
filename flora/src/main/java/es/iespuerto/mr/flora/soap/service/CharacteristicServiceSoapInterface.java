package es.iespuerto.mr.flora.soap.service;

import java.util.List;

import es.iespuerto.mr.flora.model.Characteristic;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

/**
 * This interface defines the SOAP web service operations for managing characteristics.
 * It provides methods to retrieve, create, update, and delete characteristics.
 * 
 */
@WebService(targetNamespace = "springboot.soap.service")
public interface CharacteristicServiceSoapInterface {

    /**
     * Retrieves all characteristics.
     * 
     * @return a list of all characteristics.
     */
    @WebMethod
    @WebResult(name = "characteristic")
    List<Characteristic> getAllCharacteristics();

    /**
     * Retrieves a characteristic by its ID.
     * 
     * @param characteristicId the ID of the characteristic to retrieve.
     * @return the characteristic with the specified ID.
     */
    @WebMethod
    @WebResult(name = "characteristic")
    Characteristic getCharacteristicById(@WebParam(name = "characteristicId") int characteristicId);

    /**
     * Creates a new characteristic.
     * 
     * @param characteristic the characteristic to create.
     * @return the created characteristic.
     */
    @WebMethod
    @WebResult(name = "characteristic")
    Characteristic createCharacteristic(@WebParam(name = "characteristic") Characteristic characteristic);

    /**
     * Updates an existing characteristic.
     * 
     * @param characteristicId the ID of the characteristic to update.
     * @param characteristicDetails the new details of the characteristic.
     * @return the updated characteristic.
     */
    @WebMethod
    @WebResult(name = "characteristic")
    Characteristic updateCharacteristic(@WebParam(name = "characteristicId") int characteristicId,
                                        @WebParam(name = "characteristicDetails") Characteristic characteristicDetails);

    /**
     * Deletes a characteristic by its ID.
     * 
     * @param characteristicId the ID of the characteristic to delete.
     */
    @WebMethod
    void deleteCharacteristic(@WebParam(name = "characteristicId") int characteristicId);
}
