package com.sibkm.serverapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detail_mbkm")
public class DetailMbkm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mbkm;

    @ManyToOne
    @JoinColumn(name = "id_bidang", referencedColumnName = "id_bidang")
    private Bidang bidang;

    @OneToOne
    @JoinColumn(name = "id_detail_user", referencedColumnName = "id_detail_user")
    private DetailUser detailuser;

    private String jenis;
    private String kampus;
    private String jurusan;
}
