package com.sibkm.serverapp.controller;

import com.sibkm.serverapp.entity.DetailUser;
import com.sibkm.serverapp.service.DetailUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detail-users")
public class DetailUserController {
    @Autowired
    private DetailUserService detailUserService;

    @GetMapping
    public List<DetailUser> getAllDetailUsers() {
        return detailUserService.getAllDetailUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailUser> getDetailUserById(@PathVariable Long id) {
        return detailUserService.getDetailUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetailUser createDetailUser(@RequestBody DetailUser detailUser) {
        return detailUserService.saveDetailUser(detailUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailUser> updateDetailUser(@PathVariable Long id,
            @RequestBody DetailUser detailUserDetails) {
        try {
            DetailUser updatedDetailUser = detailUserService.updateDetailUser(id, detailUserDetails);
            return ResponseEntity.ok(updatedDetailUser);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetailUser(@PathVariable Long id) {
        detailUserService.deleteDetailUser(id);
        return ResponseEntity.noContent().build();
    }
}
