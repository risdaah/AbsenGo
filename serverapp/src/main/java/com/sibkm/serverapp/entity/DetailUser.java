package com.sibkm.serverapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detail_user")
public class DetailUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detail_user;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;

    @OneToOne
    @JoinColumn(name = "id_mbkm", referencedColumnName = "id_mbkm")
    private DetailMbkm detailMbkm;

    private String namaDepan;
    private String namaBelakang;
}
