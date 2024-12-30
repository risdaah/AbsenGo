package com.sibkm.clientapp.config;

import com.sibkm.clientapp.helper.SessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestTemplateConfig {

    private final SessionManager sessionManager;

    public RestTemplateConfig(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // Tambahkan interceptor untuk menyisipkan sesi
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
                    throws IOException {
                // Dapatkan data sesi
                Long id_user = sessionManager.getId_user();

                if (id_user != null) {
                    // Tambahkan header khusus untuk sesi
                    request.getHeaders().add("X-User-Id", String.valueOf(id_user));
                }

                return execution.execute(request, body);
            }
        });

        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }
}