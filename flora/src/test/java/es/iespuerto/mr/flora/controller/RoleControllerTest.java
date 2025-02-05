package es.iespuerto.mr.flora.controller;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Role;
import es.iespuerto.mr.flora.service.RoleServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleControllerTest {

    @Mock
    private RoleServiceInterface roleService;

    @InjectMocks
    private RoleController roleController;

    private Role role;

    @BeforeEach
    void setUp() {
        role = new Role();
        role.setId(1);
        role.setName("Test Role");
    }

    @Test
    void getAllRolesReturnsListOfRoles() {
        when(roleService.getAllRols()).thenReturn(List.of(role));

        List<Role> roles = roleController.getAllRoles();

        assertNotNull(roles);
        assertEquals(1, roles.size());
        assertEquals("Test Role", roles.get(0).getName());
    }

    @Test
    void getRoleByIdReturnsRole() throws ResourceNotFoundException {
        when(roleService.getRolById(1)).thenReturn(role);

        ResponseEntity<Role> response = roleController.getRoleById(1);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test Role", response.getBody().getName());
    }

    @Test
    void getRoleByIdThrowsResourceNotFoundException() throws ResourceNotFoundException {
        when(roleService.getRolById(1)).thenThrow(new ResourceNotFoundException("Role not found"));

        assertThrows(ResourceNotFoundException.class, () -> roleController.getRoleById(1));
    }

    @Test
    void getRoleByNameReturnsRole() throws ResourceNotFoundException {
        when(roleService.getRoleByName("Test Role")).thenReturn(role);

        ResponseEntity<Role> response = roleController.getRoleByName("Test Role");

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test Role", response.getBody().getName());
    }

    @Test
    void getRoleByNameThrowsResourceNotFoundException() throws ResourceNotFoundException {
        when(roleService.getRoleByName("Test Role")).thenThrow(new ResourceNotFoundException("Role not found"));

        assertThrows(ResourceNotFoundException.class, () -> roleController.getRoleByName("Test Role"));
    }

    @Test
    void createRoleReturnsCreatedRole() {
        when(roleService.createRol(any(Role.class))).thenReturn(role);

        Role createdRole = roleController.createRole(role);

        assertNotNull(createdRole);
        assertEquals("Test Role", createdRole.getName());
    }

    @Test
    void updateRoleReturnsUpdatedRole() throws ResourceNotFoundException {
        when(roleService.updateRol(eq(1), any(Role.class))).thenReturn(role);

        ResponseEntity<Role> response = roleController.updateRole(1, role);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test Role", response.getBody().getName());
    }

    @Test
    void updateRoleThrowsResourceNotFoundException() throws ResourceNotFoundException {
        when(roleService.updateRol(eq(1), any(Role.class))).thenThrow(new ResourceNotFoundException("Role not found"));

        assertThrows(ResourceNotFoundException.class, () -> roleController.updateRole(1, role));
    }

    @Test
    void deleteRoleReturnsSuccessMap() throws ResourceNotFoundException {
        doNothing().when(roleService).deleteRol(1);

        Map<String, Boolean> response = roleController.deleteRole(1);

        assertTrue(response.get("deleted"));
    }

    @Test
    void deleteRoleThrowsResourceNotFoundException() throws ResourceNotFoundException {
        doThrow(new ResourceNotFoundException("Role not found")).when(roleService).deleteRol(1);

        assertThrows(ResourceNotFoundException.class, () -> roleController.deleteRole(1));
    }
}