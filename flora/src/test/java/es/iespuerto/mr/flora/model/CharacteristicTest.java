package es.iespuerto.mr.flora.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CharacteristicTest {

    @Test
    void defaultConstructorInitializesFieldsCorrectly() {
        Characteristic characteristic = new Characteristic();

        assertEquals(0, characteristic.getId());
        assertNull(characteristic.getDescripcion());
    }

    @Test
    void constructorWithDescriptionInitializesFieldsCorrectly() {
        Characteristic characteristic = new Characteristic("Test Description");

        assertEquals("Test Description", characteristic.getDescripcion());
    }

    @Test
    void setIdUpdatesIdCorrectly() {
        Characteristic characteristic = new Characteristic();
        characteristic.setId(1);

        assertEquals(1, characteristic.getId());
    }

    @Test
    void setDescripcionUpdatesDescriptionCorrectly() {
        Characteristic characteristic = new Characteristic();
        characteristic.setDescripcion("Updated Description");

        assertEquals("Updated Description", characteristic.getDescripcion());
    }

    @Test
    void toStringReturnsCorrectStringRepresentation() {
        Characteristic characteristic = new Characteristic("Test Description");
        characteristic.setId(1);

        assertEquals("Characteristic [id=1, descripcion=Test Description]", characteristic.toString());
    }
}