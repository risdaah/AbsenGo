package com.sibkm.serverapp.controller;

import com.sibkm.serverapp.entity.DetailMbkm;
import com.sibkm.serverapp.service.DetailMbkmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detail-mbkms")
public class DetailMbkmController {
    @Autowired
    private DetailMbkmService detailMbkmService;

    @GetMapping
    public List<DetailMbkm> getAllDetailMbkms() {
        return detailMbkmService.getAllDetailMbkms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailMbkm> getDetailMbkmById(@PathVariable Long id) {
        return detailMbkmService.getDetailMbkmById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetailMbkm createDetailMbkm(@RequestBody DetailMbkm detailMbkm) {
        return detailMbkmService.saveDetailMbkm(detailMbkm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailMbkm> updateDetailMbkm(@PathVariable Long id,
            @RequestBody DetailMbkm detailMbkmDetails) {
        try {
            DetailMbkm updatedDetailMbkm = detailMbkmService.updateDetailMbkm(id, detailMbkmDetails);
            return ResponseEntity.ok(updatedDetailMbkm);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetailMbkm(@PathVariable Long id) {
        detailMbkmService.deleteDetailMbkm(id);
        return ResponseEntity.noContent().build();
    }
}
