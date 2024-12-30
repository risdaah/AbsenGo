package com.sibkm.clientapp.service;

import com.sibkm.clientapp.entity.Absensi;
import com.sibkm.clientapp.helper.BasicHeaderHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import org.springframework.security.core.Authentication;

import java.util.List;

@Service
@Slf4j
public class AbsensiService {

    @Value("${server.base.url}/api/absensi")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    // Create Absensi dengan Authorization Header
    public Absensi create(Absensi absensi) {
        // Siapkan header otentikasi
        HttpHeaders headers = new HttpHeaders();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            // Membuat token otentikasi (Basic token)
            String token = BasicHeaderHelper.createBasicToken(auth.getName(), auth.getCredentials().toString());
            headers.add("Authorization", "Basic " + token);
        }

        // Membuat HttpEntity dengan header otentikasi dan body Absensi
        HttpEntity<Absensi> entity = new HttpEntity<>(absensi, headers);

        // Kirim POST request dengan header dan body Absensi
        return restTemplate.exchange(url, HttpMethod.POST, entity, Absensi.class).getBody();
    }

    // Get all Absensi
    public List<Absensi> getAll() {
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Absensi>>() {
        }).getBody();
    }

    // Get Absensi by ID
    public Absensi getById(Long id) {
        return restTemplate.exchange(url + "/" + id, HttpMethod.GET, null, Absensi.class).getBody();
    }

    // Get Absensi by Date
    public Absensi getByDate(String tanggal) {
        // Assuming your API has an endpoint to get Absensi by date
        return restTemplate.exchange(url + "/date/" + tanggal, HttpMethod.GET, null, Absensi.class).getBody();
    }

    // Update Absensi
    public Absensi update(Long id, Absensi absensiDetails) {
        HttpEntity<Absensi> request = new HttpEntity<>(absensiDetails);
        return restTemplate.exchange(url + "/" + id, HttpMethod.PUT, request, Absensi.class).getBody();
    }

    // Delete Absensi
    public void delete(Long id) {
        restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, null, Void.class);
    }
}