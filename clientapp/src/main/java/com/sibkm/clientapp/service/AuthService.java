package com.sibkm.clientapp.service;

import com.sibkm.clientapp.model.request.LoginRequest;
import com.sibkm.clientapp.model.response.LoginResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.sibkm.clientapp.helper.SessionManager;

@Slf4j
@Service
public class AuthService {

    @Value("${server.base.url}/auth/login")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SecurityContextRepository securityContextRepository;

    @Autowired
    private SessionManager sessionManager;

    public Boolean login(
            LoginRequest loginRequest,
            HttpServletRequest request,
            HttpServletResponse response) {
        try {
            // 1. Konsumsi endpoint login di backend
            ResponseEntity<LoginResponse> res = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    new HttpEntity<>(loginRequest),
                    LoginResponse.class);

            // 2. Cek status respons
            if (res.getStatusCode() == HttpStatus.OK) {
                LoginResponse loginResponse = res.getBody();

                if (loginResponse != null) {
                    // 3. Set prinsip dan otentikasi
                    setPrinciple(loginResponse, loginRequest.getPassword());

                    // 4. Simpan SecurityContext ke sesi
                    saveSecurityContext(request, response);

                    // 5. Simpan `id_user` ke SessionManager
                    sessionManager.setId_user(loginResponse.getId_user());
                    log.info("User ID {} saved in session.", loginResponse.getId_user());

                    // 6. Redirect berdasarkan peran pengguna
                    List<String> authorities = loginResponse.getAuthorities();
                    if (authorities.contains("ROLE_MENTOR")) {
                        response.sendRedirect("/dashboard-mentor");
                    } else if (authorities.contains("ROLE_PESERTA")) {
                        response.sendRedirect("/dashboard-peserta");
                    }

                    return true; // Login berhasil
                }
            } else {
                log.warn("Login failed with status: {}", res.getStatusCode());
            }
        } catch (Exception e) {
            log.error("Login error: {}", e.getMessage(), e);
            return false;
        }

        return false; // Login gagal
    }

    private void setPrinciple(LoginResponse response, String password) {
        // Dapatkan otoritas (roles)
        List<SimpleGrantedAuthority> authorities = response
                .getAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        // Buat token otentikasi
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                response.getUsername(),
                password,
                authorities);

        // Set otentikasi ke SecurityContext
        SecurityContextHolder.getContext().setAuthentication(token);

        log.info(
                "Authentication set for username: {}, authorities: {}",
                response.getUsername(),
                authorities);
    }

    private void saveSecurityContext(
            HttpServletRequest request,
            HttpServletResponse response) {
        // Inisialisasi SecurityContext
        SecurityContext context = SecurityContextHolder.getContext();

        // Simpan SecurityContext ke sesi
        securityContextRepository.saveContext(context, request, response);

        log.info("Security context successfully saved in session.");
    }
}