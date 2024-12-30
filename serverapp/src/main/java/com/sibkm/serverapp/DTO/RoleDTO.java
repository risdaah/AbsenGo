package com.sibkm.serverapp.DTO;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

    private Long id_role;
    private String role;
    private Set<PriviledgeDTO> priviledges;
}
