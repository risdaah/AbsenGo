package com.sibkm.serverapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bidang")
public class Bidang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_bidang;

    private String nama_bidang;

}
