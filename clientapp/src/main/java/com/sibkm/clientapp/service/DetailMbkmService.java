package com.sibkm.clientapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sibkm.clientapp.entity.DetailMbkm;
import com.sibkm.clientapp.helper.BasicHeaderHelper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DetailMbkmService {
    @Value("${server.base.url}/api/detail-mbkms")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    // Get All
    public List<DetailMbkm> getAll() {
        // Siapkan header otentikasi
        HttpHeaders headers = new HttpHeaders();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            // Membuat token otentikasi (Basic token)
            String token = BasicHeaderHelper.createBasicToken(auth.getName(), auth.getCredentials().toString());
            headers.add("Authorization", "Basic " + token);
        }

        // Membuat HttpEntity dengan header otentikasi
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Kirim GET request dengan header otentikasi
        return restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<DetailMbkm>>() {
        }).getBody();
    }

    // Get by Id
    public DetailMbkm getByid(Long id) {
        // Siapkan header otentikasi
        HttpHeaders headers = new HttpHeaders();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            // Membuat token otentikasi (Basic token)
            String token = BasicHeaderHelper.createBasicToken(auth.getName(), auth.getCredentials().toString());
            headers.add("Authorization", "Basic " + token);
        }

        // Membuat HttpEntity dengan header otentikasi
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Kirim GET request dengan header otentikasi
        return restTemplate.exchange(url.concat("/" + id), HttpMethod.GET, entity, DetailMbkm.class).getBody();
    }

    // update
    public DetailMbkm update(Long id, DetailMbkm detailMbkm) {
        // Siapkan header otentikasi
        HttpHeaders headers = new HttpHeaders();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            // Membuat token otentikasi (Basic token)
            String token = BasicHeaderHelper.createBasicToken(auth.getName(), auth.getCredentials().toString());
            headers.add("Authorization", "Basic " + token);
        }

        // Membuat HttpEntity dengan header otentikasi dan body data
        HttpEntity<DetailMbkm> request = new HttpEntity<>(detailMbkm, headers);

        // Kirim PUT request dengan header otentikasi
        return restTemplate
                .exchange(url.concat("/" + id), HttpMethod.PUT, request, DetailMbkm.class)
                .getBody();
    }
}