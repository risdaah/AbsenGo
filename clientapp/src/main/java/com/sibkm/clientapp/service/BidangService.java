package com.sibkm.clientapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sibkm.clientapp.entity.Bidang;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BidangService {

    @Value("${server.base.url}/api/bidangs")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    // Get All
    public List<Bidang> getAll() {
        return restTemplate
                .exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Bidang>>() {
                        })
                .getBody();
    }

}
