package es.iespuerto.mr.flora.soap.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Role;
import es.iespuerto.mr.flora.service.RoleServiceInterface;
import jakarta.xml.ws.WebServiceException;

public class RoleServiceSoapImplTest {

    @Mock
    private RoleServiceInterface roleService;

    @InjectMocks
    private RoleServiceSoapImpl roleServiceSoap;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllRolesReturnsAllRoles() {
        Role role1 = new Role();
        role1.setId(1);
        Role role2 = new Role();
        role2.setId(2);

        when(roleService.getAllRols()).thenReturn(Arrays.asList(role1, role2));

        List<Role> roles = roleServiceSoap.getAllRoles();

        assertEquals(2, roles.size());
        assertEquals(1, roles.get(0).getId());
        assertEquals(2, roles.get(1).getId());
    }

    @Test
    void getAllRolesThrowsWebServiceExceptionOnError() {
        when(roleService.getAllRols()).thenThrow(new RuntimeException("Error"));

        assertThrows(WebServiceException.class, () -> {
            roleServiceSoap.getAllRoles();
        });
    }

    @Test
    void getRoleByIdReturnsRoleIfExists() throws ResourceNotFoundException {
        Role role = new Role();
        role.setId(1);

        when(roleService.getRolById(1)).thenReturn(role);

        Role foundRole = roleServiceSoap.getRoleById(1);

        assertEquals(1, foundRole.getId());
    }

    @Test
    void getRoleByIdThrowsWebServiceExceptionIfRoleNotFound() throws ResourceNotFoundException {
        when(roleService.getRolById(1)).thenThrow(new ResourceNotFoundException("Role not found"));

        assertThrows(WebServiceException.class, () -> {
            roleServiceSoap.getRoleById(1);
        });
    }

    @Test
    void createRoleSavesAndReturnsRole() {
        Role role = new Role();
        role.setId(1);

        when(roleService.createRol(role)).thenReturn(role);

        Role createdRole = roleServiceSoap.createRole(role);

        assertEquals(1, createdRole.getId());
    }

    @Test
    void createRoleThrowsWebServiceExceptionOnError() {
        Role role = new Role();
        role.setId(1);

        when(roleService.createRol(role)).thenThrow(new RuntimeException("Error"));

        assertThrows(WebServiceException.class, () -> {
            roleServiceSoap.createRole(role);
        });
    }

    @Test
    void updateRoleUpdatesAndReturnsRole() throws ResourceNotFoundException {
        Role existingRole = new Role();
        existingRole.setId(1);

        Role updatedDetails = new Role();
        updatedDetails.setId(1);

        when(roleService.updateRol(1, updatedDetails)).thenReturn(existingRole);

        Role updatedRole = roleServiceSoap.updateRole(1, updatedDetails);

        assertEquals(1, updatedRole.getId());
    }

    @Test
    void updateRoleThrowsWebServiceExceptionIfRoleNotFound() throws ResourceNotFoundException {
        Role updatedDetails = new Role();
        updatedDetails.setId(1);

        when(roleService.updateRol(1, updatedDetails)).thenThrow(new ResourceNotFoundException("Role not found"));

        assertThrows(WebServiceException.class, () -> {
            roleServiceSoap.updateRole(1, updatedDetails);
        });
    }

    @Test
    void deleteRoleDeletesRoleIfExists() throws ResourceNotFoundException {
        doNothing().when(roleService).deleteRol(1);

        roleServiceSoap.deleteRole(1);

        verify(roleService, times(1)).deleteRol(1);
    }

    @Test
    void deleteRoleThrowsWebServiceExceptionIfRoleNotFound() throws ResourceNotFoundException {
        doThrow(new ResourceNotFoundException("Role not found")).when(roleService).deleteRol(1);

        assertThrows(WebServiceException.class, () -> {
            roleServiceSoap.deleteRole(1);
        });
    }
}