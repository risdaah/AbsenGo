package com.sibkm.clientapp.entity;

import lombok.Data;

@Data
public class DetailAbsensi {
    private Long id_absensi;
    private String keterangan;
    private String jam;
    private Long id_user;
    private Absensi absensi;
    private User user;
}
