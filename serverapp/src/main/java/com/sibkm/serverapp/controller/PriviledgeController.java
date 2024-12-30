package com.sibkm.serverapp.controller;

import com.sibkm.serverapp.entity.Priviledge;
import com.sibkm.serverapp.service.PriviledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/priviledges")
public class PriviledgeController {
    @Autowired
    private PriviledgeService priviledgeService;

    @GetMapping
    public List<Priviledge> getAllPriviledges() {
        return priviledgeService.getAllPriviledges();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Priviledge> getPriviledgeById(@PathVariable Long id) {
        return priviledgeService.getPriviledgeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Priviledge createPriviledge(@RequestBody Priviledge priviledge) {
        return priviledgeService.savePriviledge(priviledge);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Priviledge> updatePriviledge(@PathVariable Long id,
            @RequestBody Priviledge priviledgeDetails) {
        try {
            Priviledge updatedPriviledge = priviledgeService.updatePriviledge(id, priviledgeDetails);
            return ResponseEntity.ok(updatedPriviledge);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePriviledge(@PathVariable Long id) {
        priviledgeService.deletePriviledge(id);
        return ResponseEntity.noContent().build();
    }
}
