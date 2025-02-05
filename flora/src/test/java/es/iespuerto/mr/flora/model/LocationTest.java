package es.iespuerto.mr.flora.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {

    @Test
    void defaultConstructorInitializesFieldsCorrectly() {
        Location location = new Location();

        assertEquals(0, location.getId());
        assertNull(location.getName());
    }

    @Test
    void constructorWithNameInitializesFieldsCorrectly() {
        Location location = new Location("Test Location");

        assertEquals("Test Location", location.getName());
    }

    @Test
    void setIdUpdatesIdCorrectly() {
        Location location = new Location();
        location.setId(1);

        assertEquals(1, location.getId());
    }

    @Test
    void setNameUpdatesNameCorrectly() {
        Location location = new Location();
        location.setName("Updated Location");

        assertEquals("Updated Location", location.getName());
    }

    @Test
    void toStringReturnsCorrectStringRepresentation() {
        Location location = new Location("Test Location");
        location.setId(1);

        assertEquals("Location [id=1, name=Test Location]", location.toString());
    }
}