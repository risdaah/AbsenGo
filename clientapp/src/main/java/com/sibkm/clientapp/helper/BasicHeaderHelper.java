package com.sibkm.clientapp.helper;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BasicHeaderHelper {

    // Membuat Basic Auth token dari username dan password
    public static String createBasicToken(String username, String password) {
        String auth = username + ":" + password;
        return Base64.getEncoder()
                .encodeToString(auth.getBytes(StandardCharsets.US_ASCII));
    }

    // Membuat HTTP Headers dengan Basic Authentication
    public static HttpHeaders createBasicHeaders() {
        // Mengambil informasi autentikasi dari SecurityContext
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null || auth.getCredentials() == null) {
            throw new IllegalStateException("Authentication incomplete!!!");
        }

        // Membuat token Basic Auth dari username dan password
        String token = createBasicToken(auth.getPrincipal().toString(), auth.getCredentials().toString());

        // Menambahkan Authorization header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + token);
        return headers;
    }
}
