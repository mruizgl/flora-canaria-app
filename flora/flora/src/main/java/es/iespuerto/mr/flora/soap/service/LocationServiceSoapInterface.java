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
    Location getLocationById(@WebParam(name = "locationId") int locationId);
}