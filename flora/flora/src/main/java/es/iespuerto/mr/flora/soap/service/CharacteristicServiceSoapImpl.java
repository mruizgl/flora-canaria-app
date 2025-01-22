package es.iespuerto.mr.flora.soap.service;

import java.util.List;

import es.iespuerto.mr.flora.model.Characteristic;
import es.iespuerto.mr.flora.service.CharacteristicServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;

@WebService(endpointInterface = "es.iespuerto.mr.flora.soap.service.CharacteristicServiceSoapInterface")
public class CharacteristicServiceSoapImpl implements CharacteristicServiceSoapInterface {

    private CharacteristicServiceInterface characteristicService;

    @Autowired
    public void setCharacteristicService(CharacteristicServiceInterface characteristicService) {
        this.characteristicService = characteristicService;
    }

    @Override
    public List<Characteristic> getAllCharacteristics() {
        return characteristicService.getAllCharacteristics();
    }

    @Override
    public Characteristic getCharacteristicById(int characteristicId) {
        try {
            return characteristicService.getCharacteristicById(characteristicId);
        } catch (Exception e) {
            throw new WebServiceException("Error obteniendo la característica", e);
        }
    }

    @Override
    public Characteristic createCharacteristic(Characteristic characteristic) {
        try {
            return characteristicService.createCharacteristic(characteristic);
        } catch (Exception e) {
            throw new WebServiceException("Error creando la característica", e);
        }
    }

    @Override
    public Characteristic updateCharacteristic(int characteristicId, Characteristic characteristicDetails) {
        try {
            return characteristicService.updateCharacteristic(characteristicId, characteristicDetails);
        } catch (Exception e) {
            throw new WebServiceException("Error actualizando la característica", e);
        }
    }

    @Override
    public void deleteCharacteristic(int characteristicId) {
        try {
            characteristicService.deleteCharacteristic(characteristicId);
        } catch (Exception e) {
            throw new WebServiceException("Error eliminando la característica", e);
        }
    }
}
