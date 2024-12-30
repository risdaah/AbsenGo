package com.sibkm.serverapp.controller;

import com.sibkm.serverapp.entity.Absensi;
import com.sibkm.serverapp.service.AbsensiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/absensi")
public class AbsensiController {
    @Autowired
    private AbsensiService absensiService;

    @GetMapping
    public List<Absensi> getAllAbsensi() {
        return absensiService.getAllAbsensi();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Absensi> getAbsensiById(@PathVariable Long id) {
        return absensiService.getAbsensiById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Absensi createAbsensi(@RequestBody Absensi absensi) {
        return absensiService.saveAbsensi(absensi);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Absensi> updateAbsensi(@PathVariable Long id, @RequestBody Absensi absensiDetails) {
        try {
            Absensi updatedAbsensi = absensiService.updateAbsensi(id, absensiDetails);
            return ResponseEntity.ok(updatedAbsensi);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbsensi(@PathVariable Long id) {
        absensiService.deleteAbsensi(id);
        return ResponseEntity.noContent().build();
    }
}
