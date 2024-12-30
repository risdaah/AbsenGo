package com.sibkm.serverapp.service;

import com.sibkm.serverapp.DTO.RoleDTO;
import com.sibkm.serverapp.entity.Role;
import com.sibkm.serverapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<RoleDTO> getAllRoleDTOs() {
        return roleRepository.findAllRoleDTOs();
    }

    public Role getById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role with ID " + id + " not found"));
    }

    public Optional<Role> getByRole(String role) {
        return roleRepository.findByRole(role);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Long id, Role roleDetails) {
        return roleRepository.findById(id).map(role -> {
            role.setRole(roleDetails.getRole());
            return roleRepository.save(role);
        }).orElseThrow(() -> new RuntimeException("Role not found with id " + id));
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

}
