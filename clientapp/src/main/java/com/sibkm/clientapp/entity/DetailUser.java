package com.sibkm.clientapp.entity;

import lombok.Data;

@Data
public class DetailUser {
    private Long id_detail_user;
    private String namaDepan;
    private String namaBelakang;
    private DetailMbkm detailMbkm;
    private User user;
}
