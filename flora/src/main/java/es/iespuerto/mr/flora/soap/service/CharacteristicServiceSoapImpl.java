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
}