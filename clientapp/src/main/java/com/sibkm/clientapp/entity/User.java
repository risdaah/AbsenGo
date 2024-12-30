package com.sibkm.clientapp.entity;

import lombok.Data;

@Data
public class User {
    private Long id_user;
    private String username;
    private String password;
    private String email;
    private Long roleIds;
    private DetailUser detailUser;
}