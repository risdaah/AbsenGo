package com.sibkm.clientapp.service;

import com.sibkm.clientapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Value("${server.base.url}/api/users")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public List<User> getAll() {
        // Konsumsi endpoint dengan RestTemplate
        List<User> users = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                })
                .getBody();

        log.info("Retrieved {} users", users != null ? users.size() : 0);
        return users;
    }
}