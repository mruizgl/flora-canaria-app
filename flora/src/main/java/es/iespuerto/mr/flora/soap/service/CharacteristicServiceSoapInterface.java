package es.iespuerto.mr.flora.soap.service;


import es.iespuerto.mr.flora.model.Characteristic;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService(targetNamespace = "springboot.soap.service")
public interface CharacteristicServiceSoapInterface {

    @WebMethod
    @WebResult(name = "characteristic")
    List<Characteristic> getAllCharacteristics();

    @WebMethod
    @WebResult(name = "characteristic")
    Characteristic getCharacteristicById(@WebParam(name = "characteristicId") int characteristicId);
}