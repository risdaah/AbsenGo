package com.sibkm.clientapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MentorController {

    // menu melihat rekap peserta
    @GetMapping("/rekap-peserta")
    public String rekapPeserta() {
        return "mentor/dataRekapPeserta";
    }

    // menu melihat info peserta
    // @GetMapping("/info-peserta")
    // public String infoPeserta() {
    // return "mentor/dataPeserta";
    // }

    // menu mengelola agenda
    // @GetMapping("/kelola-agenda")
    // public String kelolaAgenda() {
    // return "mentor/dataAgenda";
    // }

}
