package com.sibkm.clientapp.controller;

import com.sibkm.clientapp.model.request.RegistrationRequest;
import com.sibkm.clientapp.service.AuthService;
import com.sibkm.clientapp.service.RegisService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final RegisService regisService;

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("registrationRequest", new RegistrationRequest());
        return "registration"; // Mengembalikan view untuk form registrasi
    }

    @PostMapping("/registration")
    public String register(RegistrationRequest registrationRequest, HttpServletRequest request,
            HttpServletResponse response) {
        regisService.registration(registrationRequest); // Panggil service untuk registrasi
        return "redirect:/login"; // Redirect ke halaman login setelah registrasi berhasil
    }
}