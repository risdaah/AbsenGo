package com.sibkm.serverapp.repository;

import com.sibkm.serverapp.DTO.RoleDTO;
import com.sibkm.serverapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(String role);

    @Query("SELECT new com.sibkm.serverapp.DTO.RoleDTO(r.id, r.role, " +
            "(SELECT new com.sibkm.serverapp.DTO.PriviledgeDTO(p.id, p.priviledge) " +
            "FROM Priviledge p WHERE element (p.roles).id = r.id)) " +
            "FROM Role r")
    List<RoleDTO> findAllRoleDTOs();
}
