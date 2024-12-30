package com.sibkm.clientapp.entity;

import lombok.Data;

@Data
public class DetailMbkm {
    private Long id_mbkm;
    private String jenis;
    private String kampus;
    private String jurusan;
    private Bidang bidang;
}