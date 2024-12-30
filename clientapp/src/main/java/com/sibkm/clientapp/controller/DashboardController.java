package com.sibkm.clientapp.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sibkm.clientapp.entity.DetailAbsensi;
// import org.springframework.web.bind.annotation.RequestParam;
// import com.sibkm.clientapp.helper.SessionManager;
// import com.sibkm.clientapp.entity.DetailAbsensi;
import com.sibkm.clientapp.service.DetailAbsensiService;
import com.sibkm.clientapp.service.GetIdUserService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DashboardController {
    @Autowired
    private DetailAbsensiService detailAbsensiService;

    @Autowired
    private GetIdUserService getIdUserService;

    @GetMapping("/dashboard-peserta")
    public String dashboardPeserta(Model model, Principal principal) {
        // Ambil username dari Principal
        String username = principal.getName();

        // Ambil id_user dari service
        Long idUser = getIdUserService.getIdByUsername(username);

        // Kirimkan data ke view
        model.addAttribute("username", username);
        model.addAttribute("id_user", idUser);
        return "peserta/dashboardPeserta";
    }

    // @GetMapping("/dashboard-mentor")
    // public String dashboardPeserta(Model model) {
    // // Ambil rekap data absensi tanpa memerlukan username atau autentikasi
    // model.addAttribute("rekapList", detailAbsensiService.getAll());
    // return "mentor/dashboardMentor"; // Kembalikan view
    // }

    // menampilkan data based on date
    // Di dalam method controller
    @GetMapping("/dashboard-mentor")
    public String dashboardMentor(Model model) {
        // Ambil rekap data absensi
        List<DetailAbsensi> rekapList = detailAbsensiService.getAll();

        // Ambil tanggal saat ini
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Kirim data ke model
        model.addAttribute("rekapList", rekapList);
        model.addAttribute("currentDate", formattedDate); // Kirim tanggal ke view

        return "mentor/dashboardMentor"; // Kembalikan view
    }

    @GetMapping("/rekap-peserta")
    public String rekapPeserta(Model model) {
        model.addAttribute("rekapList", detailAbsensiService.getAll());
        return "mentor/dataRekapPeserta"; // Kembalikan view
    }

}
