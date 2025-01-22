package es.iespuerto.mr.flora.soap.service;

import java.util.List;

import es.iespuerto.mr.flora.model.Location;
import es.iespuerto.mr.flora.service.LocationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;

@WebService(endpointInterface = "es.iespuerto.mr.flora.soap.service.LocationServiceSoapInterface")
public class LocationServiceSoapImpl implements LocationServiceSoapInterface {

    private LocationServiceInterface locationService;

    @Autowired
    public void setLocationService(LocationServiceInterface locationService) {
        this.locationService = locationService;
    }

    @Override
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @Override
    public Location getLocationById(int locationId) {
        try {
            return locationService.getLocationById(locationId);
        } catch (Exception e) {
            throw new WebServiceException("Error obteniendo la ubicación", e);
        }
    }

    @Override
    public Location createLocation(Location location) {
        try {
            return locationService.createLocation(location);
        } catch (Exception e) {
            throw new WebServiceException("Error creando la ubicación", e);
        }
    }

    @Override
    public Location updateLocation(int locationId, Location locationDetails) {
        try {
            return locationService.updateLocation(locationId, locationDetails);
        } catch (Exception e) {
            throw new WebServiceException("Error actualizando la ubicación", e);
        }
    }

    @Override
    public void deleteLocation(int locationId) {
        try {
            locationService.deleteLocation(locationId);
        } catch (Exception e) {
            throw new WebServiceException("Error eliminando la ubicación", e);
        }
    }
}
