package es.iespuerto.mr.flora.controller;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Characteristic;
import es.iespuerto.mr.flora.service.CharacteristicServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador que maneja las operaciones CRUD para las características.
 * <p>
 * Este controlador permite obtener, crear, actualizar y eliminar características en la aplicación.
 * </p>
 */
@RestController
@RequestMapping("/api/v1")
public class CharacteristicsController {

    private CharacteristicServiceInterface characteristicService;

    /**
     * Inyección de dependencias para el servicio de características.
     *
     * @param characteristicService el servicio que gestiona las características.
     */
    @Autowired
    public void setCharacteristicService(CharacteristicServiceInterface characteristicService) {
        this.characteristicService = characteristicService;
    }

    /**
     * Obtiene todas las características de la base de datos.
     *
     * @return una lista de todas las características.
     */
    @Operation(summary = "Get all characteristics")
    @GetMapping("/characteristics/")
    public List<Characteristic> getAllCharacteristics() {
        return characteristicService.getAllCharacteristics();
    }

    /**
     * Obtiene una característica por su ID.
     *
     * @param characteristicId el ID de la característica a obtener.
     * @return una respuesta con la característica solicitada.
     * @throws ResourceNotFoundException si no se encuentra la característica con el ID proporcionado.
     */
    @Operation(summary = "Get characteristic by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Characteristic not found")
    })
    @GetMapping("/characteristic/{id}")
    public ResponseEntity<Characteristic> getCharacteristicById(@PathVariable(value = "id") int characteristicId) throws ResourceNotFoundException {
        Characteristic characteristic = characteristicService.getCharacteristicById(characteristicId);
        return ResponseEntity.ok().body(characteristic);
    }

    /**
     * Crea una nueva característica.
     *
     * @param characteristic los datos de la característica a crear.
     * @return la característica creada.
     */
    @Operation(summary = "Insert characteristic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Characteristic created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add/characteristic/")
    public Characteristic createCharacteristic(@Valid @RequestBody Characteristic characteristic) {
        return characteristicService.createCharacteristic(characteristic);
    }

    /**
     * Actualiza una característica existente.
     *
     * @param characteristicId el ID de la característica a actualizar.
     * @param characteristicDetails los nuevos detalles de la característica.
     * @return la característica actualizada.
     * @throws ResourceNotFoundException si no se encuentra la característica con el ID proporcionado.
     */
    @Operation(summary = "Update characteristic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Characteristic updated successfully"),
            @ApiResponse(responseCode = "404", description = "Characteristic not found")
    })
    @PutMapping("/update/characteristic/{id}")
    public ResponseEntity<Characteristic> updateCharacteristic(@PathVariable(value = "id") int characteristicId,
                                                               @Valid @RequestBody Characteristic characteristicDetails) throws ResourceNotFoundException {
        final Characteristic updatedCharacteristic = characteristicService.updateCharacteristic(characteristicId, characteristicDetails);
        return ResponseEntity.ok(updatedCharacteristic);
    }

    /**
     * Elimina una característica por su ID.
     *
     * @param characteristicId el ID de la característica a eliminar.
     * @return una respuesta indicando si la característica fue eliminada con éxito.
     * @throws ResourceNotFoundException si no se encuentra la característica con el ID proporcionado.
     */
    @Operation(summary = "Delete characteristic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Characteristic deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Characteristic not found")
    })
    @DeleteMapping("/delete/characteristic/{id}")
    public Map<String, Boolean> deleteCharacteristic(@PathVariable(value = "id") int characteristicId) throws ResourceNotFoundException {
        characteristicService.deleteCharacteristic(characteristicId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
