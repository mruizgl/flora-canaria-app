package es.iespuerto.mr.flora.soap.service;

import java.util.List;

import es.iespuerto.mr.flora.model.Role;
import es.iespuerto.mr.flora.service.RoleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;

@WebService(endpointInterface = "es.iespuerto.mr.flora.soap.service.RoleServiceSoapInterface")
public class RoleServiceSoapImpl implements RoleServiceSoapInterface {

    private RoleServiceInterface roleService;

    @Autowired
    public void setRoleService(RoleServiceInterface roleService) {
        this.roleService = roleService;
    }

    @Override
    public List<Role> getAllRoles() {
        try {
            return roleService.getAllRols();
        } catch (Exception e) {
            throw new WebServiceException("Error obteniendo los roles", e);
        }
    }

    @Override
    public Role getRoleById(int roleId) {
        try {
            return roleService.getRolById(roleId);
        } catch (Exception e) {
            throw new WebServiceException("Error obteniendo el rol con ID: " + roleId, e);
        }
    }

    @Override
    public Role createRole(Role role) {
        try {
            return roleService.createRol(role);
        } catch (Exception e) {
            throw new WebServiceException("Error creando el rol", e);
        }
    }

    @Override
    public Role updateRole(int roleId, Role roleDetails) {
        try {
            return roleService.updateRol(roleId, roleDetails);
        } catch (Exception e) {
            throw new WebServiceException("Error actualizando el rol con ID: " + roleId, e);
        }
    }

    @Override
    public void deleteRole(int roleId) {
        try {
            roleService.deleteRol(roleId);
        } catch (Exception e) {
            throw new WebServiceException("Error eliminando el rol con ID: " + roleId, e);
        }
    }
}
