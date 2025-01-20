package es.iespuerto.mr.flora.soap.service;

import java.util.List;

import es.iespuerto.mr.flora.model.Plant;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

@WebService(targetNamespace = "springboot.soap.service")
public interface PlantServiceSoapInterface {

    @WebMethod
    @WebResult(name = "plant")
    List<Plant> getAllPlants();

    @WebMethod
    Plant getPlantById(@WebParam(name = "plantId") int plantId);

    @WebMethod
    Plant createPlant(@WebParam(name = "plant") Plant plant);

    @WebMethod
    Plant updatePlant(@WebParam(name = "plantId") int plantId, @WebParam(name = "plantDetails") Plant plantDetails);

    @WebMethod
    void deletePlant(@WebParam(name = "plantId") int plantId);
}