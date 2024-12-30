package com.sibkm.clientapp.service;

import com.sibkm.clientapp.model.request.RegistrationRequest;
import com.sibkm.clientapp.model.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RegisService {
    @Value("${server.base.url}/auth/registration")
    private String registrationUrl;

    @Autowired
    private RestTemplate restTemplate;

    public void registration(RegistrationRequest registrationRequest) {
        // Membuat HttpEntity dengan body registrasi
        HttpEntity<RegistrationRequest> requestEntity = new HttpEntity<>(registrationRequest);

        // Mengirimkan permintaan POST ke backend
        ResponseEntity<BaseResponse> response = restTemplate.exchange(
                registrationUrl,
                HttpMethod.POST,
                requestEntity,
                BaseResponse.class);

        // Cek status respons
        if (response.getStatusCode().is2xxSuccessful()) {
            // Registrasi berhasil
            System.out.println("Registrasi berhasil: " + response.getBody());
        } else {
            // Registrasi gagal
            System.out.println("Registrasi gagal: " + response.getStatusCode());
        }
    }
}
