package com.sibkm.clientapp.service;

import com.sibkm.clientapp.entity.DetailAbsensi;
import com.sibkm.clientapp.helper.BasicHeaderHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

import org.springframework.security.core.Authentication;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Service
@Slf4j
public class DetailAbsensiService {
    @Value("${server.base.url}/api/detail-absensi")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    // Get All
    public List<DetailAbsensi> getAll() {
        return restTemplate
                .exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<DetailAbsensi>>() {
                })
                .getBody();

    }

    // Get Absensi by ID
    // public DetailAbsensi getById(Long id) {
    // return restTemplate.exchange(url + "/" + id, HttpMethod.GET, null,
    // DetailAbsensi.class).getBody();
    // }

    // Create DetailAbsensi
    public DetailAbsensi create(DetailAbsensi detailAbsensi, Long id_user) {
        // Set id_user in the DetailAbsensi object
        detailAbsensi.setId_user(id_user);

        // Siapkan header otentikasi
        HttpHeaders headers = new HttpHeaders();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            // Membuat token otentikasi (Basic token)
            String token = BasicHeaderHelper.createBasicToken(auth.getName(), auth.getCredentials().toString());
            headers.add("Authorization", "Basic " + token);
        }

        // Membuat HttpEntity dengan header otentikasi dan body DetailAbsensi
        HttpEntity<DetailAbsensi> entity = new HttpEntity<>(detailAbsensi, headers);

        // Kirim POST request dengan header dan body DetailAbsensi
        return restTemplate.exchange(url, HttpMethod.POST, entity, DetailAbsensi.class).getBody();
    }

}
