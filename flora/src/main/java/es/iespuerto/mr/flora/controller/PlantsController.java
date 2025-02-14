package es.iespuerto.mr.flora.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Plant;
import es.iespuerto.mr.flora.service.PlantServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

/**
 * Controlador que maneja las operaciones CRUD para las plantas.
 * 
 * Este controlador permite obtener, crear, actualizar y eliminar plantas en la aplicación.
 * 
 *
 * @author Melissa Ruiz
 */
@RestController
@RequestMapping("/api/v1")
public class PlantsController {

    private PlantServiceInterface plantService;

    /**
     * Inyección de dependencias para el servicio de plantas.
     *
     * @param plantService el servicio que gestiona las plantas.
     */
    @Autowired
    public void setPlantService(PlantServiceInterface plantService) {
        this.plantService = plantService;
    }

    /**
     * Obtiene todas las plantas de la base de datos.
     *
     * @return una lista de todas las plantas.
     */
    @Operation(summary = "Get all plants")
    @GetMapping("/plants/")
    public List<Plant> getAllPlants() {
        return plantService.getAllPlants();
    }

    /**
     * Obtiene una planta por su ID.
     *
     * @param plantId el ID de la planta a obtener.
     * @return una respuesta con la planta solicitada.
     * @throws ResourceNotFoundException si no se encuentra la planta con el ID proporcionado.
     */
    @Operation(summary = "Get plant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Plant not found")
    })
    @GetMapping("/plant/{id}")
    public ResponseEntity<Plant> getPlantById(@PathVariable(value = "id") int plantId) throws ResourceNotFoundException {
        Plant plant = plantService.getPlantById(plantId);
        return ResponseEntity.ok().body(plant);
    }

    /**
     * Crea una nueva planta.
     *
     * @param plant los datos de la planta a crear.
     * @return la planta creada.
     */
    @Operation(summary = "Insert plant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plant created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add/plant/")
    public Plant createPlant(@Valid @RequestBody Plant plant) {
        return plantService.createPlant(plant);
    }

    /**
     * Actualiza una planta existente.
     *
     * @param plantId el ID de la planta a actualizar.
     * @param plantDetails los nuevos detalles de la planta.
     * @return la planta actualizada.
     * @throws ResourceNotFoundException si no se encuentra la planta con el ID proporcionado.
     */
    @Operation(summary = "Update plant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plant updated successfully"),
            @ApiResponse(responseCode = "404", description = "Plant not found")
    })
    @PutMapping("/update/plant/{id}")
    public ResponseEntity<Plant> updatePlant(@PathVariable(value = "id") int plantId,
                                             @Valid @RequestBody Plant plantDetails) throws ResourceNotFoundException {
        final Plant updatedPlant = plantService.updatePlant(plantId, plantDetails);
        return ResponseEntity.ok(updatedPlant);
    }

    /**
     * Elimina una planta por su ID.
     *
     * @param plantId el ID de la planta a eliminar.
     * @return una respuesta indicando si la planta fue eliminada con éxito.
     * @throws ResourceNotFoundException si no se encuentra la planta con el ID proporcionado.
     */
    @Operation(summary = "Delete plant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plant deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Plant not found")
    })
    @DeleteMapping("/delete/plant/{id}")
    public Map<String, Boolean> deletePlant(@PathVariable(value = "id") int plantId) throws ResourceNotFoundException {
        plantService.deletePlant(plantId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
