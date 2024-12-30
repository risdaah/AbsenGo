package com.sibkm.serverapp.DTO;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id_user;
    private String username;
    private String password;
    private String email;
    private Set<Long> roleIds;
}
