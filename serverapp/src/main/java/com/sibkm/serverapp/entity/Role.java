package com.sibkm.serverapp.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_role;

    private String role;

    @ManyToMany(mappedBy = "role")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<User> users;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "tb_tr_role_priviledge", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "priviledge_id"))
    private List<Priviledge> priviledges;
}
