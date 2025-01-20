package es.iespuerto.mr.flora.service;

import java.util.List;

import es.iespuerto.mr.flora.exception.ResourceNotFoundException;
import es.iespuerto.mr.flora.model.Role;
import es.iespuerto.mr.flora.model.User;
import es.iespuerto.mr.flora.repository.RoleRepository;
import es.iespuerto.mr.flora.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Component
public class RoleService implements RoleServiceInterface {
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public List<Role> getAllRols() {
        log.info("Realizando la llamada al servicio");
        return roleRepository.findAll();
    }

    @Override
    public Role getRolById(@PathVariable(value = "id") int roleId) throws ResourceNotFoundException {
        return roleRepository.getById(roleId);
    }

    @Override
    public Role createRol(@Valid @RequestBody Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRol(@PathVariable(value = "id") int rolId, @Valid @RequestBody Role roleDetails) throws ResourceNotFoundException {
        Role role = roleRepository.findById(rolId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + rolId));

        role.setName(roleDetails.getName());
        return roleRepository.save(role);
    }

    @Override
    public void deleteRol(@PathVariable(value = "id") int rolId) throws ResourceNotFoundException {
        Role role = roleRepository.findById(rolId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found for this id :: " + rolId));

        roleRepository.delete(role);
    }
}
