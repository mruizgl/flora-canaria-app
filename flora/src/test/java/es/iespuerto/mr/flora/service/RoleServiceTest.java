package es.iespuerto.mr.flora.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Role;
import es.iespuerto.mr.flora.repository.RoleRepository;

public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllRolsReturnsAllRoles() {
        Role role1 = new Role();
        role1.setName("Role 1");
        Role role2 = new Role();
        role2.setName("Role 2");

        when(roleRepository.findAll()).thenReturn(Arrays.asList(role1, role2));

        List<Role> roles = roleService.getAllRols();

        assertEquals(2, roles.size());
        assertEquals("Role 1", roles.get(0).getName());
        assertEquals("Role 2", roles.get(1).getName());
    }



    @Test
    void createRolSavesAndReturnsRole() {
        Role role = new Role();
        role.setName("New Role");

        when(roleRepository.save(role)).thenReturn(role);

        Role createdRole = roleService.createRol(role);

        assertEquals("New Role", createdRole.getName());
    }

    @Test
    void updateRolUpdatesAndReturnsRole() throws ResourceNotFoundException {
        Role existingRole = new Role();
        existingRole.setId(1);
        existingRole.setName("Existing Role");

        Role updatedDetails = new Role();
        updatedDetails.setName("Updated Role");

        when(roleRepository.findById(1)).thenReturn(Optional.of(existingRole));
        when(roleRepository.save(existingRole)).thenReturn(existingRole);

        Role updatedRole = roleService.updateRol(1, updatedDetails);

        assertEquals("Updated Role", updatedRole.getName());
    }

    @Test
    void updateRolThrowsExceptionIfRoleDoesNotExist() {
        Role updatedDetails = new Role();
        updatedDetails.setName("Updated Role");

        when(roleRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            roleService.updateRol(1, updatedDetails);
        });
    }

    @Test
    void deleteRolDeletesRoleIfExists() throws ResourceNotFoundException {
        Role role = new Role();
        role.setId(1);
        role.setName("Role to Delete");

        when(roleRepository.findById(1)).thenReturn(Optional.of(role));
        doNothing().when(roleRepository).delete(role);

        roleService.deleteRol(1);

        verify(roleRepository, times(1)).delete(role);
    }

    @Test
    void deleteRolThrowsExceptionIfRoleDoesNotExist() {
        when(roleRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            roleService.deleteRol(1);
        });
    }

    @Test
    void getRoleByNameReturnsRoleIfExists() throws ResourceNotFoundException {
        Role role = new Role();
        role.setName("Test Role");

        when(roleRepository.findByName("Test Role")).thenReturn(Optional.of(role));

        Role foundRole = roleService.getRoleByName("Test Role");

        assertEquals("Test Role", foundRole.getName());
    }

    @Test
    void getRoleByNameThrowsExceptionIfRoleDoesNotExist() {
        when(roleRepository.findByName("Nonexistent Role")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            roleService.getRoleByName("Nonexistent Role");
        });
    }
}