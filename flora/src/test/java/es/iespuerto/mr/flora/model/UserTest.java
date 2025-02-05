package es.iespuerto.mr.flora.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void defaultConstructorInitializesFieldsCorrectly() {
        User user = new User();

        assertEquals(0, user.getId());
        assertNull(user.getName());
        assertNull(user.getPassword());
        assertNull(user.getRole());
    }

    @Test
    void constructorWithNameAndPasswordInitializesFieldsCorrectly() {
        User user = new User("John Doe", "password123");

        assertEquals("John Doe", user.getName());
        assertEquals("password123", user.getPassword());
    }

    @Test
    void setIdUpdatesIdCorrectly() {
        User user = new User();
        user.setId(1);

        assertEquals(1, user.getId());
    }

    @Test
    void setNameUpdatesNameCorrectly() {
        User user = new User();
        user.setName("Jane Doe");

        assertEquals("Jane Doe", user.getName());
    }

    @Test
    void setPasswordUpdatesPasswordCorrectly() {
        User user = new User();
        user.setPassword("newpassword");

        assertEquals("newpassword", user.getPassword());
    }

    @Test
    void setRoleUpdatesRoleCorrectly() {
        User user = new User();
        Role role = new Role("Admin");
        user.setRole(role);

        assertEquals(role, user.getRole());
    }

    @Test
    void toStringReturnsCorrectStringRepresentation() {
        User user = new User("John Doe", "password123");
        user.setId(1);

        assertEquals("User [id=1, name=John Doe, password=password123]", user.toString());
    }
}