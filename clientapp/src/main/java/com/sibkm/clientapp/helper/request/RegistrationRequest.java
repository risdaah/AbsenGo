package com.sibkm.clientapp.helper.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
    private String username;
    private String password;
    private String email;
    private String namaDepan;
    private String namaBelakang;
    private String jenis;
    private String kampus;
    private String jurusan;
    private String namaBidang;
}
