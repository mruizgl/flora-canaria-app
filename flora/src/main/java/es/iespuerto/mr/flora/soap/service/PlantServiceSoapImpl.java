package es.iespuerto.mr.flora.soap.service;

import es.iespuerto.mr.flora.model.Plant;
import es.iespuerto.mr.flora.service.PlantServiceInterface;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@WebService(endpointInterface = "es.iespuerto.mr.flora.soap.service.PlantServiceSoapInterface")
public class PlantServiceSoapImpl implements PlantServiceSoapInterface {

    private PlantServiceInterface plantService;

    @Autowired
    public void setPlantService(PlantServiceInterface plantService) {
        this.plantService = plantService;
    }

    @Override
    public List<Plant> getAllPlants() {
        return plantService.getAllPlants();
    }

    @Override
    public Plant getPlantById(int plantId) {
        try {
            return plantService.getPlantById(plantId);
        } catch (Exception e) {
            throw new WebServiceException("Error obteniendo la planta", e);
        }
    }

    @Override
    public Plant createPlant(Plant plant) {
        try {
            return plantService.createPlant(plant);
        } catch (Exception e) {
            throw new WebServiceException("Error creando la planta", e);
        }
    }

    @Override
    public Plant updatePlant(int plantId, Plant plantDetails) {
        try {
            return plantService.updatePlant(plantId, plantDetails);
        } catch (Exception e) {
            throw new WebServiceException("Error actualizando la planta", e);
        }
    }

    @Override
    public void deletePlant(int plantId) {
        try {
            plantService.deletePlant(plantId);
        } catch (Exception e) {
            throw new WebServiceException("Error eliminando la planta", e);
        }
    }
}
