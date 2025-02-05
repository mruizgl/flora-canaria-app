package es.iespuerto.mr.flora.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Location;
import es.iespuerto.mr.flora.repository.LocationRepository;

public class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationService locationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllLocationsReturnsAllLocations() {
        Location location1 = new Location();
        location1.setName("Location 1");
        Location location2 = new Location();
        location2.setName("Location 2");

        when(locationRepository.findAll()).thenReturn(Arrays.asList(location1, location2));

        List<Location> locations = locationService.getAllLocations();

        assertEquals(2, locations.size());
        assertEquals("Location 1", locations.get(0).getName());
        assertEquals("Location 2", locations.get(1).getName());
    }

    @Test
    void getLocationByIdReturnsLocationIfExists() throws ResourceNotFoundException {
        Location location = new Location();
        location.setId(1);
        location.setName("Test Location");

        when(locationRepository.findById(1)).thenReturn(Optional.of(location));

        Location foundLocation = locationService.getLocationById(1);

        assertEquals(1, foundLocation.getId());
        assertEquals("Test Location", foundLocation.getName());
    }

    @Test
    void getLocationByIdThrowsExceptionIfLocationDoesNotExist() {
        when(locationRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            locationService.getLocationById(1);
        });
    }

    @Test
    void createLocationSavesAndReturnsLocation() {
        Location location = new Location();
        location.setName("New Location");

        when(locationRepository.save(location)).thenReturn(location);

        Location createdLocation = locationService.createLocation(location);

        assertEquals("New Location", createdLocation.getName());
    }

    @Test
    void updateLocationUpdatesAndReturnsLocation() throws ResourceNotFoundException {
        Location existingLocation = new Location();
        existingLocation.setId(1);
        existingLocation.setName("Existing Location");

        Location updatedDetails = new Location();
        updatedDetails.setName("Updated Location");

        when(locationRepository.findById(1)).thenReturn(Optional.of(existingLocation));
        when(locationRepository.save(existingLocation)).thenReturn(existingLocation);

        Location updatedLocation = locationService.updateLocation(1, updatedDetails);

        assertEquals("Updated Location", updatedLocation.getName());
    }

    @Test
    void updateLocationThrowsExceptionIfLocationDoesNotExist() {
        Location updatedDetails = new Location();
        updatedDetails.setName("Updated Location");

        when(locationRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            locationService.updateLocation(1, updatedDetails);
        });
    }

    @Test
    void deleteLocationDeletesLocationIfExists() throws ResourceNotFoundException {
        Location location = new Location();
        location.setId(1);
        location.setName("Location to Delete");

        when(locationRepository.findById(1)).thenReturn(Optional.of(location));
        doNothing().when(locationRepository).delete(location);

        locationService.deleteLocation(1);

        verify(locationRepository, times(1)).delete(location);
    }

    @Test
    void deleteLocationThrowsExceptionIfLocationDoesNotExist() {
        when(locationRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            locationService.deleteLocation(1);
        });
    }
}