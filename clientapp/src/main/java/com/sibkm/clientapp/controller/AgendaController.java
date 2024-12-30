package com.sibkm.clientapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sibkm.clientapp.entity.Agenda;
import com.sibkm.clientapp.service.AgendaService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/kelola-agenda")
public class AgendaController {
    private AgendaService agendaService;

    // Menampilkan semua data agenda
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("agendaList", agendaService.getAll());
        return "mentor/dataAgenda"; // Ganti dengan nama template Anda
    }

    // Menambahkan data agenda
    @PostMapping("/tambah")
    public String tambahAgenda(@ModelAttribute Agenda agenda) {
        agendaService.create(agenda);
        return "redirect:/kelola-agenda";
    }

    // Menampilkan form edit agenda
    @GetMapping("/edit/{id}")
    public String editAgenda(@PathVariable Long id, Model model) {
        model.addAttribute("agenda", agendaService.getByid(id));
        return "mentor/formEditAgenda"; // Ganti dengan nama template Anda
    }

    // Mengedit data agenda
    @PutMapping("/edit/{id}")
    public String updateAgenda(@PathVariable Long id, @ModelAttribute Agenda agenda) {
        agendaService.update(id, agenda); // Mengupdate agenda di database
        return "redirect:/kelola-agenda"; // Redirect ke halaman setelah update
    }

    // Menghapus data agenda
    @GetMapping("/delete/{id}")
    public String deleteAgenda(@PathVariable Long id) {
        agendaService.delete(id);
        return "redirect:/kelola-agenda";
    }
}