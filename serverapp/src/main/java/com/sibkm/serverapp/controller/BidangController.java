package com.sibkm.serverapp.controller;

import com.sibkm.serverapp.entity.Bidang;
import com.sibkm.serverapp.service.BidangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bidangs")
public class BidangController {
    @Autowired
    private BidangService bidangService;

    @GetMapping
    public List<Bidang> getAllBidangs() {
        return bidangService.getAllBidangs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bidang> getBidangById(@PathVariable Long id) {
        return bidangService.getBidangById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Bidang createBidang(@RequestBody Bidang bidang) {
        return bidangService.saveBidang(bidang);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bidang> updateBidang(@PathVariable Long id, @RequestBody Bidang bidangDetails) {
        try {
            Bidang updatedBidang = bidangService.updateBidang(id, bidangDetails);
            return ResponseEntity.ok(updatedBidang);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBidang(@PathVariable Long id) {
        bidangService.deleteBidang(id);
        return ResponseEntity.noContent().build();
    }
}
