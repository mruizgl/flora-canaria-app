package es.iespuerto.mr.flora.service;

import java.util.List;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Characteristic;
import es.iespuerto.mr.flora.repository.CharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CharacteristicService implements CharacteristicServiceInterface {

    private CharacteristicRepository characteristicRepository;

    @Autowired
    public void setCharacteristicRepository(CharacteristicRepository characteristicRepository) {
        this.characteristicRepository = characteristicRepository;
    }

    @Override
    public List<Characteristic> getAllCharacteristics() {
        log.info("Realizando la llamada al servicio");
        return characteristicRepository.findAll();
    }

    @Override
    public Characteristic getCharacteristicById(@PathVariable(value = "id") int characteristicId) throws ResourceNotFoundException {
        return characteristicRepository.findById(characteristicId)
                .orElseThrow(() -> new ResourceNotFoundException("Characteristic not found for this id :: " + characteristicId));
    }

    @Override
    public Characteristic createCharacteristic(@Valid @RequestBody Characteristic characteristic) {
        return characteristicRepository.save(characteristic);
    }

    @Override
    public Characteristic updateCharacteristic(@PathVariable(value = "id") int characteristicId,
                                               @Valid @RequestBody Characteristic characteristicDetails) throws ResourceNotFoundException {
        Characteristic characteristic = characteristicRepository.findById(characteristicId)
                .orElseThrow(() -> new ResourceNotFoundException("Characteristic not found for this id :: " + characteristicId));

        characteristic.setDescripcion(characteristicDetails.getDescripcion());
        return characteristicRepository.save(characteristic);
    }

    @Override
    public void deleteCharacteristic(@PathVariable(value = "id") int characteristicId) throws ResourceNotFoundException {
        Characteristic characteristic = characteristicRepository.findById(characteristicId)
                .orElseThrow(() -> new ResourceNotFoundException("Characteristic not found for this id :: " + characteristicId));

        characteristicRepository.delete(characteristic);
    }

}
