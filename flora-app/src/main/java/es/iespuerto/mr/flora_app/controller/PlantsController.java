package es.iespuerto.mr.flora_app.controller;

import es.iespuerto.mr.flora_app.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora_app.model.Plant;
import es.iespuerto.mr.flora_app.service.PlantServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PlantsController {

    private PlantServiceInterface plantService;

    @Autowired
    public void setPlantService(PlantServiceInterface plantService) {
        this.plantService = plantService;
    }

    @Operation(summary = "Get all plants")
    @GetMapping("/plants/")
    public List<Plant> getAllPlants() {
        return plantService.getAllPlants();
    }

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

    @Operation(summary = "Insert plant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plant created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add/plant/")
    public Plant createPlant(@Valid @RequestBody Plant plant) {
        return plantService.createPlant(plant);
    }

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
