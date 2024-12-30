package com.sibkm.clientapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.sibkm.clientapp.entity.Absensi;
import com.sibkm.clientapp.entity.DetailAbsensi;
import com.sibkm.clientapp.service.AbsensiService;
import com.sibkm.clientapp.service.DetailAbsensiService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/dashboard-peserta")
public class AbsensiController {
    @Autowired
    private AbsensiService absensiService;

    @Autowired
    private DetailAbsensiService detailAbsensiService;

    @PostMapping("/absen")
    public String createAbsensiAndDetailAbsensi(DetailAbsensi detailAbsensi,
            String tanggal, Long id_user) {

        // Create Absensi
        Absensi absensi = new Absensi();
        absensi.setTanggal(tanggal); // Set the date from the form
        Absensi createdAbsensi = absensiService.create(absensi); // Create Absensi

        // Create DetailAbsensi
        detailAbsensi.setAbsensi(createdAbsensi); // Link DetailAbsensi to Absensi
        detailAbsensiService.create(detailAbsensi, id_user); // Pass id_user to the service method

        return "redirect:/dashboard-peserta"; // Redirect to the dashboard or any other page
    }

    // @PostMapping("/absen")
    // public String createAbsensiAndDetailAbsensi(
    // @RequestParam String jam,
    // @RequestParam String keterangan,
    // @RequestParam Long id_user) {

    // // Logging untuk memeriksa nilai yang diterima
    // log.info("Menerima request untuk membuat absensi dengan:");
    // log.info("ID User: {}", id_user);
    // log.info("Jam: {}", jam);
    // log.info("Keterangan: {}", keterangan);

    // // Create Absensi
    // Absensi absensi = new Absensi();
    // absensi.setTanggal(LocalDate.now().toString()); // Set the date from the form
    // Absensi createdAbsensi = absensiService.create(absensi); // Create Absensi

    // // Create DetailAbsensi
    // DetailAbsensi detailAbsensi = new DetailAbsensi();
    // detailAbsensi.setKeterangan(keterangan);
    // detailAbsensi.setJam(jam);
    // detailAbsensi.setAbsensi(createdAbsensi); // Link DetailAbsensi to Absensi

    // // Set id_user pada DetailAbsensi
    // detailAbsensi.setId_user(id_user); // Pastikan id_user diatur

    // // Simpan DetailAbsensi
    // detailAbsensiService.create(detailAbsensi, id_user); // Pass id_user to the
    // service method

    // return "redirect:/dashboard-peserta"; // Redirect to the dashboard or any
    // other page
    // }

}