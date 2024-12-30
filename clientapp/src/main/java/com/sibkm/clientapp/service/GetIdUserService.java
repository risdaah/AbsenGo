package com.sibkm.clientapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.sibkm.clientapp.helper.BasicHeaderHelper; // Import untuk BasicHeaderHelper
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetIdUserService {
    @Value("${server.base.url}/api/users/id-by-username")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public Long getIdByUsername(String username) {
        // Menyiapkan header otentikasi
        HttpHeaders headers = new HttpHeaders();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            String token = BasicHeaderHelper.createBasicToken(auth.getName(), auth.getCredentials().toString());
            headers.add("Authorization", "Basic " + token);
        }

        // Membuat HttpEntity dengan header otentikasi
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            // Mengirim request ke backend
            return restTemplate.exchange(url + "?username=" + username, HttpMethod.GET, entity, Long.class).getBody();
        } catch (HttpClientErrorException e) {
            log.error("Error during request: " + e.getStatusCode() + " " + e.getResponseBodyAsString());
            throw e; // Handling error jika terjadi
        }
    }
}
