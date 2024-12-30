package com.sibkm.clientapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sibkm.clientapp.entity.Agenda;
import com.sibkm.clientapp.helper.BasicHeaderHelper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

@Service
@Slf4j
public class AgendaService {

        @Value("${server.base.url}/api/agendas") // URL API
        private String url;

        private final RestTemplate restTemplate;

        public AgendaService(RestTemplate restTemplate) {
                this.restTemplate = restTemplate;
        }

        // Menyiapkan header otentikasi
        private HttpHeaders createAuthHeaders() {
                HttpHeaders headers = new HttpHeaders();
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth != null && auth.isAuthenticated()) {
                        // Membuat token otentikasi (Basic token)
                        String token = BasicHeaderHelper.createBasicToken(auth.getName(),
                                        auth.getCredentials().toString());
                        headers.add("Authorization", "Basic " + token);
                }
                return headers;
        }

        // Get All
        public List<Agenda> getAll() {
                HttpHeaders headers = createAuthHeaders();
                return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers),
                                new ParameterizedTypeReference<List<Agenda>>() {
                                }).getBody();
        }

        // Create Agenda
        public Agenda create(Agenda agenda) {
                HttpHeaders headers = createAuthHeaders();
                return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(agenda, headers), Agenda.class)
                                .getBody();
        }

        // Get By Id
        public Agenda getByid(Long id) {
                HttpHeaders headers = createAuthHeaders();
                return restTemplate
                                .exchange(url.concat("/" + id), HttpMethod.GET, new HttpEntity<>(headers), Agenda.class)
                                .getBody();
        }

        // Update Agenda
        public Agenda update(Long id, Agenda agenda) {
                HttpHeaders headers = createAuthHeaders();
                return restTemplate.exchange(url.concat("/" + id), HttpMethod.PUT, new HttpEntity<>(agenda, headers),
                                Agenda.class).getBody();
        }

        // Delete Agenda
        public void delete(Long id) {
                HttpHeaders headers = createAuthHeaders();
                restTemplate.exchange(url.concat("/" + id), HttpMethod.DELETE, new HttpEntity<>(headers), Void.class);
        }
}