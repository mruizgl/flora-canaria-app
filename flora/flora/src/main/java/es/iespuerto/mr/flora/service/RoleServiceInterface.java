package es.iespuerto.mr.flora.service;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Role;
import es.iespuerto.mr.flora.model.User;

import java.util.List;

public interface RoleServiceInterface {
    List<Role> getAllRols();
    Role getRolById(int roleId) throws ResourceNotFoundException;
    Role createRol(Role role);
    Role updateRol(int rolId, Role roleDetails)       throws ResourceNotFoundException;
    void deleteRol(int rolId) throws ResourceNotFoundException;
}
