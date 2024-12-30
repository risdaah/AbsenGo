package com.sibkm.serverapp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "priviledge")
public class Priviledge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_priviledge;

    private String priviledge;

    @ManyToMany(mappedBy = "priviledges")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Role> roles;
}
