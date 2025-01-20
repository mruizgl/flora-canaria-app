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
}
