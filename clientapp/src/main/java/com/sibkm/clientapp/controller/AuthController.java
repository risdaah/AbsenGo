package com.sibkm.clientapp.controller;

import com.sibkm.clientapp.model.request.LoginRequest;
import com.sibkm.clientapp.model.request.RegistrationRequest;
import com.sibkm.clientapp.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/login")
public class AuthController {

    private AuthService authService;

    @GetMapping
    public String loginView(
            LoginRequest loginRequest,
            HttpServletRequest request,
            HttpServletResponse response) {
        // ? check princple
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
            log.info("Success login...");
            // log.info("Name: {}", auth.getName());
            return "redirect:/dashboard-mentor";
        } else {
            // salah
            log.info("Authentication failed!!!");
        }

        log.info("Display page login...");
        return "login";
    }

    // @PostMapping
    // public String login(
    // @ModelAttribute LoginRequest loginRequest,
    // HttpServletRequest request,
    // HttpServletResponse response) {
    // boolean loginSuccess = authService.login(loginRequest, request, response);

    // // gagal login => /login?error=true
    // if (!loginSuccess) {
    // log.info("Login error!!!");
    // return "redirect:/login?error=true";
    // }

    // return "redirect:/dashboard-mentor";
    // }

    @PostMapping
    public String login(
            @ModelAttribute LoginRequest loginRequest,
            HttpServletRequest request,
            HttpServletResponse response) {
        boolean loginSuccess = authService.login(loginRequest, request, response);

        // gagal login => /login?error=true
        if (!loginSuccess) {
            return "redirect:/login?error=true";
        }

        // Redirect berdasarkan role
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_MENTOR"))) {
            return "mentor/dashboardMentor";
        } else if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_PESERTA"))) {
            return "peserta/dashboardPeserta";
        }

        return "redirect:/welcome"; // Redirect ke halaman default jika tidak ada role yang cocok
    }

}