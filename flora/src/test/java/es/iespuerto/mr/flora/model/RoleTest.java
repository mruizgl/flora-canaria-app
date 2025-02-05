package es.iespuerto.mr.flora.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RoleTest {

    @Test
    void defaultConstructorInitializesFieldsCorrectly() {
        Role role = new Role();

        assertEquals(0, role.getId());
        assertNull(role.getName());
        assertNull(role.getUsers());
    }

    @Test
    void constructorWithNameInitializesFieldsCorrectly() {
        Role role = new Role("Admin");

        assertEquals("Admin", role.getName());
    }

    @Test
    void setIdUpdatesIdCorrectly() {
        Role role = new Role();
        role.setId(1);

        assertEquals(1, role.getId());
    }

    @Test
    void setNameUpdatesNameCorrectly() {
        Role role = new Role();
        role.setName("User");

        assertEquals("User", role.getName());
    }

    @Test
    void setUsersUpdatesUsersCorrectly() {
        Role role = new Role();
        Set<User> users = new HashSet<>();
        role.setUsers(users);

        assertEquals(users, role.getUsers());
    }

    @Test
    void toStringReturnsCorrectStringRepresentation() {
        Role role = new Role("Admin");
        role.setId(1);

        assertEquals("Role [id=1, name=Admin]", role.toString());
    }
}