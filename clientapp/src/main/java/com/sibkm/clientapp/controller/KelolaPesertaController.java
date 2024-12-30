package com.sibkm.clientapp.controller;

import com.sibkm.clientapp.entity.DetailUser;
import com.sibkm.clientapp.service.DetailMbkmService;
// import com.sibkm.clientapp.service.BidangService;
// import com.sibkm.clientapp.service.DetailMbkmService;
import com.sibkm.clientapp.service.DetailUserService;
import lombok.AllArgsConstructor;

//import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/peserta")
public class KelolaPesertaController {
    @Autowired
    private DetailUserService detailUserService;

    @Autowired
    private DetailMbkmService detailMbkmService;

    // Menampilkan semua data peserta
    @GetMapping
    public String getAll(Model model) {
        System.out.println("Request received for /peserta");
        model.addAttribute("detailList", detailUserService.getAll());
        return "mentor/dataPeserta";
    }

    // Menampilkan form edir data agenda
    @GetMapping("/edit/{id}")
    public String editPeserta(@PathVariable Long id, Model model) {
        model.addAttribute("detail", detailUserService.getByid(id));
        return "mentor/formEditPeserta";
    }

    // Mengedit data peserta
    // @PutMapping("/edit/{id}")
    // public String updatePeserta(@PathVariable Long id, @ModelAttribute DetailUser
    // detailUser) {
    // detailUserService.update(id, detailUser); // Mengupdate detailUser di
    // database
    // return "redirect:/peserta"; // Redirect ke halaman setelah update
    // }

    @PutMapping("/edit/{id}")
    public String updatePeserta(@PathVariable Long id, @ModelAttribute DetailUser detailUser) {
        // Update DetailUser
        detailUserService.update(id, detailUser); // Mengupdate detailUser di database

        // Update DetailMbkm jika ada perubahan
        // if (detailUser.getDetailMbkm() != null) {
        // // Panggil API untuk mengupdate DetailMbkm
        // detailMbkmService.update(detailUser.getDetailMbkm().getId_mbkm(),
        // detailUser.getDetailMbkm());
        // }

        return "redirect:/peserta"; // Redirect ke halaman setelah update
    }
}