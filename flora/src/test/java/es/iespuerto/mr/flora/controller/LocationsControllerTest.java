package es.iespuerto.mr.flora.controller;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Location;
import es.iespuerto.mr.flora.service.LocationServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocationsControllerTest {

    @Mock
    private LocationServiceInterface locationService;

    @InjectMocks
    private LocationsController locationsController;

    private Location location;

    @BeforeEach
    void setUp() {
        location = new Location();
        location.setId(1);
        location.setName("Test Location");
    }

    @Test
    void getAllLocationsReturnsListOfLocations() {
        when(locationService.getAllLocations()).thenReturn(List.of(location));

        List<Location> locations = locationsController.getAllLocations();

        assertNotNull(locations);
        assertEquals(1, locations.size());
        assertEquals("Test Location", locations.get(0).getName());
    }

    @Test
    void getLocationByIdReturnsLocation() throws ResourceNotFoundException {
        when(locationService.getLocationById(1)).thenReturn(location);

        ResponseEntity<Location> response = locationsController.getLocationById(1);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test Location", response.getBody().getName());
    }

    @Test
    void getLocationByIdThrowsResourceNotFoundException() throws ResourceNotFoundException {
        when(locationService.getLocationById(1)).thenThrow(new ResourceNotFoundException("Location not found"));

        assertThrows(ResourceNotFoundException.class, () -> locationsController.getLocationById(1));
    }

    @Test
    void createLocationReturnsCreatedLocation() {
        when(locationService.createLocation(any(Location.class))).thenReturn(location);

        Location createdLocation = locationsController.createLocation(location);

        assertNotNull(createdLocation);
        assertEquals("Test Location", createdLocation.getName());
    }

    @Test
    void updateLocationReturnsUpdatedLocation() throws ResourceNotFoundException {
        when(locationService.updateLocation(eq(1), any(Location.class))).thenReturn(location);

        ResponseEntity<Location> response = locationsController.updateLocation(1, location);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test Location", response.getBody().getName());
    }

    @Test
    void updateLocationThrowsResourceNotFoundException() throws ResourceNotFoundException {
        when(locationService.updateLocation(eq(1), any(Location.class))).thenThrow(new ResourceNotFoundException("Location not found"));

        assertThrows(ResourceNotFoundException.class, () -> locationsController.updateLocation(1, location));
    }

    @Test
    void deleteLocationReturnsSuccessMap() throws ResourceNotFoundException {
        doNothing().when(locationService).deleteLocation(1);

        Map<String, Boolean> response = locationsController.deleteLocation(1);

        assertTrue(response.get("deleted"));
    }

    @Test
    void deleteLocationThrowsResourceNotFoundException() throws ResourceNotFoundException {
        doThrow(new ResourceNotFoundException("Location not found")).when(locationService).deleteLocation(1);

        assertThrows(ResourceNotFoundException.class, () -> locationsController.deleteLocation(1));
    }
}