package com.sibkm.serverapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "detail_absensi")
public class DetailAbsensi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_absensi;

    private String keterangan;
    private String jam;

    @ManyToOne
    @JoinColumn(name = "id_user",  nullable = false)
    private User user;

    // Menggunakan insertable=false dan updatable=false untuk menghindari duplikasi
    @ManyToOne
    @JoinColumn(name = "id_absensi", referencedColumnName = "id_absensi", insertable = false, updatable = false)
    private Absensi absensi;
}