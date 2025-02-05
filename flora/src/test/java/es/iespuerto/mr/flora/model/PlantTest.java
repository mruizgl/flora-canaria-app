package es.iespuerto.mr.flora.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlantTest {

    @Test
    void defaultConstructorInitializesFieldsCorrectly() {
        Plant plant = new Plant();

        assertEquals(0, plant.getId());
        assertNull(plant.getCommonName());
        assertNull(plant.getScientificName());
    }

    @Test
    void constructorWithCommonAndScientificNameInitializesFieldsCorrectly() {
        Plant plant = new Plant("Rose", "Rosa");

        assertEquals("Rose", plant.getCommonName());
        assertEquals("Rosa", plant.getScientificName());
    }

    @Test
    void setIdUpdatesIdCorrectly() {
        Plant plant = new Plant();
        plant.setId(1);

        assertEquals(1, plant.getId());
    }

    @Test
    void setCommonNameUpdatesCommonNameCorrectly() {
        Plant plant = new Plant();
        plant.setCommonName("Updated Common Name");

        assertEquals("Updated Common Name", plant.getCommonName());
    }

    @Test
    void setScientificNameUpdatesScientificNameCorrectly() {
        Plant plant = new Plant();
        plant.setScientificName("Updated Scientific Name");

        assertEquals("Updated Scientific Name", plant.getScientificName());
    }

    @Test
    void toStringReturnsCorrectStringRepresentation() {
        Plant plant = new Plant("Rose", "Rosa");
        plant.setId(1);

        assertEquals("Plant [id=1, commonName=Rose, scientificName=Rosa]", plant.toString());
    }
}