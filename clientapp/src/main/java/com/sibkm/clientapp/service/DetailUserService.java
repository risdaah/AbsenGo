package com.sibkm.clientapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.sibkm.clientapp.entity.DetailUser;
import com.sibkm.clientapp.helper.BasicHeaderHelper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DetailUserService {

    @Value("${server.base.url}/api/detail-users")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    private HttpHeaders createAuthHeaders() {
        HttpHeaders headers = new HttpHeaders();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            String token = BasicHeaderHelper.createBasicToken(auth.getName(), auth.getCredentials().toString());
            headers.add("Authorization", "Basic " + token);
        }
        return headers;
    }

    // Get All
    public List<DetailUser> getAll() {
        HttpHeaders headers = createAuthHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<DetailUser>>() {
        }).getBody();
    }

    // Get By Id
    public DetailUser getByid(Long id) {
        HttpHeaders headers = createAuthHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url.concat("/" + id), HttpMethod.GET, entity, DetailUser.class).getBody();
    }

    // Update
    public DetailUser update(Long id, DetailUser detailUser) {
        HttpHeaders headers = createAuthHeaders();
        HttpEntity<DetailUser> request = new HttpEntity<>(detailUser, headers);

        return restTemplate.exchange(url.concat("/" + id), HttpMethod.PUT, request, DetailUser.class).getBody();
    }
}