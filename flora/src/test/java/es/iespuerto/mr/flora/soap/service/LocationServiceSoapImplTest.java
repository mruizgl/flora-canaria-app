package es.iespuerto.mr.flora.soap.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Location;
import es.iespuerto.mr.flora.service.LocationServiceInterface;
import jakarta.xml.ws.WebServiceException;

public class LocationServiceSoapImplTest {

    @Mock
    private LocationServiceInterface locationService;

    @InjectMocks
    private LocationServiceSoapImpl locationServiceSoap;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllLocationsReturnsAllLocations() {
        Location location1 = new Location();
        location1.setId(1);
        Location location2 = new Location();
        location2.setId(2);

        when(locationService.getAllLocations()).thenReturn(Arrays.asList(location1, location2));

        List<Location> locations = locationServiceSoap.getAllLocations();

        assertEquals(2, locations.size());
        assertEquals(1, locations.get(0).getId());
        assertEquals(2, locations.get(1).getId());
    }



    @Test
    void getLocationByIdReturnsLocationIfExists() throws ResourceNotFoundException {
        Location location = new Location();
        location.setId(1);

        when(locationService.getLocationById(1)).thenReturn(location);

        Location foundLocation = locationServiceSoap.getLocationById(1);

        assertEquals(1, foundLocation.getId());
    }

    @Test
    void getLocationByIdThrowsWebServiceExceptionIfLocationNotFound() throws ResourceNotFoundException {
        when(locationService.getLocationById(1)).thenThrow(new ResourceNotFoundException("Location not found"));

        assertThrows(WebServiceException.class, () -> {
            locationServiceSoap.getLocationById(1);
        });
    }

    @Test
    void createLocationSavesAndReturnsLocation() {
        Location location = new Location();
        location.setId(1);

        when(locationService.createLocation(location)).thenReturn(location);

        Location createdLocation = locationServiceSoap.createLocation(location);

        assertEquals(1, createdLocation.getId());
    }

    @Test
    void createLocationThrowsWebServiceExceptionOnError() {
        Location location = new Location();
        location.setId(1);

        when(locationService.createLocation(location)).thenThrow(new RuntimeException("Error"));

        assertThrows(WebServiceException.class, () -> {
            locationServiceSoap.createLocation(location);
        });
    }

    @Test
    void updateLocationUpdatesAndReturnsLocation() throws ResourceNotFoundException {
        Location existingLocation = new Location();
        existingLocation.setId(1);

        Location updatedDetails = new Location();
        updatedDetails.setId(1);

        when(locationService.updateLocation(1, updatedDetails)).thenReturn(existingLocation);

        Location updatedLocation = locationServiceSoap.updateLocation(1, updatedDetails);

        assertEquals(1, updatedLocation.getId());
    }

    @Test
    void updateLocationThrowsWebServiceExceptionIfLocationNotFound() throws ResourceNotFoundException {
        Location updatedDetails = new Location();
        updatedDetails.setId(1);

        when(locationService.updateLocation(1, updatedDetails)).thenThrow(new ResourceNotFoundException("Location not found"));

        assertThrows(WebServiceException.class, () -> {
            locationServiceSoap.updateLocation(1, updatedDetails);
        });
    }

    @Test
    void deleteLocationDeletesLocationIfExists() throws ResourceNotFoundException {
        doNothing().when(locationService).deleteLocation(1);

        locationServiceSoap.deleteLocation(1);

        verify(locationService, times(1)).deleteLocation(1);
    }

    @Test
    void deleteLocationThrowsWebServiceExceptionIfLocationNotFound() throws ResourceNotFoundException {
        doThrow(new ResourceNotFoundException("Location not found")).when(locationService).deleteLocation(1);

        assertThrows(WebServiceException.class, () -> {
            locationServiceSoap.deleteLocation(1);
        });
    }
}