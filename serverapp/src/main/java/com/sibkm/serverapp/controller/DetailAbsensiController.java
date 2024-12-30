package com.sibkm.serverapp.controller;

import com.sibkm.serverapp.entity.DetailAbsensi;
import com.sibkm.serverapp.service.DetailAbsensiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detail-absensi")
public class DetailAbsensiController {
    @Autowired
    private DetailAbsensiService detailAbsensiService;

    @GetMapping
    public List<DetailAbsensi> getAllDetailAbsensi() {
        return detailAbsensiService.getAllDetailAbsensi();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailAbsensi> getDetailAbsensiById(@PathVariable Long id) {
        return detailAbsensiService.getDetailAbsensiById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // get by username
    @GetMapping("/by-username")
    public ResponseEntity<List<DetailAbsensi>> getDetailAbsensiByUsername(@RequestParam String username) {
        List<DetailAbsensi> absensiList = detailAbsensiService.getDetailAbsensiByUsername(username);
        if (absensiList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(absensiList);
    }

    @PostMapping
    public ResponseEntity<DetailAbsensi> createDetailAbsensi(
            @RequestParam Long userId,
            @RequestParam Long absensiId,
            @RequestParam String keterangan,
            @RequestParam String jam) {
        DetailAbsensi detailAbsensi = detailAbsensiService.createDetailAbsensi(userId, absensiId, keterangan, jam);
        return ResponseEntity.ok(detailAbsensi);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailAbsensi> updateDetailAbsensi(@PathVariable Long id,
            @RequestBody DetailAbsensi detailAbsensiDetails) {
        try {
            DetailAbsensi updatedDetailAbsensi = detailAbsensiService.updateDetailAbsensi(id, detailAbsensiDetails);
            return ResponseEntity.ok(updatedDetailAbsensi);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetailAbsensi(@PathVariable Long id) {
        detailAbsensiService.deleteDetailAbsensi(id);
        return ResponseEntity.noContent().build();
    }
}