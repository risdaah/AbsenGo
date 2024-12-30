
package com.sibkm.clientapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

//import org.springframework.web.bind.annotation.PathVariable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.sibkm.clientapp.entity.DetailAbsensi;
import com.sibkm.clientapp.entity.DetailUser;
import com.sibkm.clientapp.helper.SessionManager;
import com.sibkm.clientapp.service.AgendaService;
import com.sibkm.clientapp.service.DetailUserService;
import com.sibkm.clientapp.service.GetIdUserService;
import com.sibkm.clientapp.service.KustomDetailAbsensiService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class PesertaController {
    // private DetailUserService detailUserService;
    private AgendaService agendaService;
    private KustomDetailAbsensiService kustomDetailAbsensi;
    private DetailUserService detailUserService;

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private GetIdUserService getIdUserService;

    @GetMapping("/profil")
    public String userProfile(Model model, Principal principal) {
        // Ambil username dari Principal
        String username = principal.getName();

        Long idUser = getIdUserService.getIdByUsername(username);

        // Tambahkan username ke model
        model.addAttribute("id_user", idUser);

        model.addAttribute("detail", detailUserService.getByid(idUser));

        return "peserta/profil"; // Render template profile.html
    }

    @GetMapping("/profil/edit/{id}")
    public String editProfil(Model model, Principal principal) {
        // Ambil username dari Principal
        String username = principal.getName();

        Long idUser = getIdUserService.getIdByUsername(username);

        // Tambahkan username ke model
        model.addAttribute("id_user", idUser);

        model.addAttribute("detail", detailUserService.getByid(idUser));

        return "peserta/editProfil"; // Render template profile.html
    }

    @PutMapping("/profil/edit/{id}")
    public String updatePeserta(@PathVariable Long id, @ModelAttribute DetailUser detailUser) {
        // Update DetailUser
        detailUserService.update(id, detailUser); // Mengupdate detailUser di database

        return "redirect:/profil"; // Redirect ke halaman setelah update
    }

    @GetMapping("/rekap")
    public String getMyRekap(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("username", username);

        // Ambil data absensi berdasarkan username
        List<DetailAbsensi> absensiList = kustomDetailAbsensi.getAll(username);

        // Jika data kosong, kirim list kosong
        if (absensiList.isEmpty()) {
            model.addAttribute("message", "Data absensi tidak ditemukan.");
        } else {
            model.addAttribute("absensiList", absensiList);
        }

        return "peserta/rekap";
    }

    // Menampilkan semua data agenda untuk peserta
    @GetMapping("/list-agenda")
    public String getAllAgenda(Model model) {
        model.addAttribute("agendaList", agendaService.getAll());
        return "peserta/agenda"; // Ganti dengan nama template Anda
    }
}