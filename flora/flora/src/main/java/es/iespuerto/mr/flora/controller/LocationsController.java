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

@RestController
@RequestMapping("/api/v1")
public class LocationsController {

    private LocationServiceInterface locationService;

    @Autowired
    public void setLocationService(LocationServiceInterface locationService) {
        this.locationService = locationService;
    }

    @Operation(summary = "Get all locations")
    @GetMapping("/locations/")
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

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

    @Operation(summary = "Insert location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add/location/")
    public Location createLocation(@Valid @RequestBody Location location) {
        return locationService.createLocation(location);
    }

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
