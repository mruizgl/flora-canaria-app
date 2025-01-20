package es.iespuerto.mr.flora.soap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.iespuerto.mr.flora.model.Role;
import es.iespuerto.mr.flora.service.RoleServiceInterface;
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


}
