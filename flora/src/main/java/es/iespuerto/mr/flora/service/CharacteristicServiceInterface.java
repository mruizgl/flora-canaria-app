package es.iespuerto.mr.flora.service;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Characteristic;

import java.util.List;

public interface CharacteristicServiceInterface {
    List<Characteristic> getAllCharacteristics();
    Characteristic getCharacteristicById(int characteristicId) throws ResourceNotFoundException;
    Characteristic createCharacteristic(Characteristic characteristic);
    Characteristic updateCharacteristic(int characteristicId, Characteristic characteristicDetails) throws ResourceNotFoundException;
    void deleteCharacteristic(int characteristicId) throws ResourceNotFoundException;
}