package es.iespuerto.mr.flora.service;

import java.util.List;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Location;
import es.iespuerto.mr.flora.repository.LocationRepository;
import org.apache.cxf.annotations.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LocationService implements LocationServiceInterface {

    private LocationRepository locationRepository;

    @Autowired
    public void setLocationRepository(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> getAllLocations() {
        log.info("Realizando la llamada al servicio");
        return locationRepository.findAll();
    }

    @Override
    public Location getLocationById(@PathVariable(value = "id") int locationId) throws ResourceNotFoundException {
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found for this id :: " + locationId));
    }

    @Override
    public Location createLocation(@Valid @RequestBody Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location updateLocation(@PathVariable(value = "id") int locationId,
                                   @Valid @RequestBody Location locationDetails) throws ResourceNotFoundException {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found for this id :: " + locationId));

        location.setName(locationDetails.getName());
        return locationRepository.save(location);
    }

    @Override
    public void deleteLocation(@PathVariable(value = "id") int locationId) throws ResourceNotFoundException {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found for this id :: " + locationId));

        locationRepository.delete(location);
    }
}
