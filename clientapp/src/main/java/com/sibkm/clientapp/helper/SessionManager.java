package com.sibkm.clientapp.helper;

import org.springframework.stereotype.Component;

@Component
public class SessionManager {
    private Long id_user;

    // Setter untuk id_user
    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    // Getter untuk id_user
    public Long getId_user() {
        return id_user;
    }

    // Clear session
    public void clearSession() {
        this.id_user = null;
    }
}