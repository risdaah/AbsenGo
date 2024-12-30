package com.sibkm.clientapp.service;

import java.util.List;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.sibkm.clientapp.entity.DetailAbsensi;
import com.sibkm.clientapp.helper.BasicHeaderHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KustomDetailAbsensiService {

    @Value("${server.base.url}/api/detail-absensi/by-username")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    // Get All
    public List<DetailAbsensi> getAll(String username) {
        // Menggunakan username dalam query parameter
        String requestUrl = url + "?username=" + username;

        // Menyiapkan header otentikasi jika diperlukan
        HttpHeaders headers = new HttpHeaders();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            String token = BasicHeaderHelper.createBasicToken(auth.getName(), auth.getCredentials().toString());
            headers.add("Authorization", "Basic " + token);
        }

        // Membuat HttpEntity dengan header otentikasi
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            // Mengirimkan request dan menerima response
            List<DetailAbsensi> response = restTemplate.exchange(
                    requestUrl,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<DetailAbsensi>>() {
                    }).getBody();

            // Jika response kosong, kembalikan list kosong
            if (response == null || response.isEmpty()) {
                return Collections.emptyList(); // Mengembalikan list kosong
            }
            return response; // Mengembalikan hasil jika ada data

        } catch (HttpClientErrorException.NotFound e) {
            log.error("Data tidak ditemukan untuk username: " + username);
            return Collections.emptyList(); // Mengembalikan list kosong jika data tidak ditemukan
        } catch (Exception e) {
            log.error("Terjadi error saat mengambil data absensi: " + e.getMessage());
            return Collections.emptyList(); // Mengembalikan list kosong jika terjadi error lainnya
        }
    }
}
