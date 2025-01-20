package es.iespuerto.mr.flora.controller;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Location;
import es.iespuerto.mr.flora.service.LocationServiceInterface;
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
 * Controlador que maneja las operaciones CRUD para las ubicaciones.
 * <p>
 * Este controlador permite obtener, crear, actualizar y eliminar ubicaciones en la aplicación.
 * </p>
 *
 * @autor Melissa Ruiz
 */
@RestController
@RequestMapping("/api/v1")
public class LocationsController {

    private LocationServiceInterface locationService;

    /**
     * Inyección de dependencias para el servicio de ubicaciones.
     *
     * @param locationService el servicio que gestiona las ubicaciones.
     */
    @Autowired
    public void setLocationService(LocationServiceInterface locationService) {
        this.locationService = locationService;
    }

    /**
     * Obtiene todas las ubicaciones de la base de datos.
     *
     * @return una lista de todas las ubicaciones.
     */
    @Operation(summary = "Get all locations")
    @GetMapping("/locations/")
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    /**
     * Obtiene una ubicación por su ID.
     *
     * @param locationId el ID de la ubicación a obtener.
     * @return una respuesta con la ubicación solicitada.
     * @throws ResourceNotFoundException si no se encuentra la ubicación con el ID proporcionado.
     */
    @Operation(summary = "Get location by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Location not found")
    })
    @GetMapping("/location/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable(value = "id") int locationId) throws ResourceNotFoundException {
        Location location = locationService.getLocationById(locationId);
        return ResponseEntity.ok().body(location);
    }

    /**
     * Crea una nueva ubicación.
     *
     * @param location los datos de la ubicación a crear.
     * @return la ubicación creada.
     */
    @Operation(summary = "Insert location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add/location/")
    public Location createLocation(@Valid @RequestBody Location location) {
        return locationService.createLocation(location);
    }

    /**
     * Actualiza una ubicación existente.
     *
     * @param locationId el ID de la ubicación a actualizar.
     * @param locationDetails los nuevos detalles de la ubicación.
     * @return la ubicación actualizada.
     * @throws ResourceNotFoundException si no se encuentra la ubicación con el ID proporcionado.
     */
    @Operation(summary = "Update location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location updated successfully"),
            @ApiResponse(responseCode = "404", description = "Location not found")
    })
    @PutMapping("/update/location/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable(value = "id") int locationId,
                                                   @Valid @RequestBody Location locationDetails) throws ResourceNotFoundException {
        final Location updatedLocation = locationService.updateLocation(locationId, locationDetails);
        return ResponseEntity.ok(updatedLocation);
    }

    /**
     * Elimina una ubicación por su ID.
     *
     * @param locationId el ID de la ubicación a eliminar.
     * @return una respuesta indicando si la ubicación fue eliminada con éxito.
     * @throws ResourceNotFoundException si no se encuentra la ubicación con el ID proporcionado.
     */
    @Operation(summary = "Delete location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Location not found")
    })
    @DeleteMapping("/delete/location/{id}")
    public Map<String, Boolean> deleteLocation(@PathVariable(value = "id") int locationId) throws ResourceNotFoundException {
        locationService.deleteLocation(locationId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
