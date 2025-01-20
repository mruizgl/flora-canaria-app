package es.iespuerto.mr.flora.service;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Location;

import java.util.List;

public interface LocationServiceInterface {
    List<Location> getAllLocations();
    Location getLocationById(int locationId) throws ResourceNotFoundException;
    Location createLocation(Location location);
    Location updateLocation(int locationId, Location locationDetails) throws ResourceNotFoundException;
    void deleteLocation(int locationId) throws ResourceNotFoundException;
}
