package es.iespuerto.mr.flora.soap.service;

import es.iespuerto.mr.flora.model.Location;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService(targetNamespace = "springboot.soap.service")
public interface LocationServiceSoapInterface {

    @WebMethod
    @WebResult(name = "location")
    List<Location> getAllLocations();

    @WebMethod
    @WebResult(name = "location")
    Location getLocationById(@WebParam(name = "locationId") int locationId);

    @WebMethod
    @WebResult(name = "location")
    Location createLocation(@WebParam(name = "location") Location location);

    @WebMethod
    @WebResult(name = "location")
    Location updateLocation(@WebParam(name = "locationId") int locationId,
                            @WebParam(name = "locationDetails") Location locationDetails);

    @WebMethod
    void deleteLocation(@WebParam(name = "locationId") int locationId);
}
