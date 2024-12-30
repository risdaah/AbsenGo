package com.sibkm.serverapp.service;

import com.sibkm.serverapp.entity.Priviledge;
import com.sibkm.serverapp.repository.PriviledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriviledgeService {
    @Autowired
    private PriviledgeRepository priviledgeRepository;

    public List<Priviledge> getAllPriviledges() {
        return priviledgeRepository.findAll();
    }

    public Optional<Priviledge> getPriviledgeById(Long id) {
        return priviledgeRepository.findById(id);
    }

    public Priviledge savePriviledge(Priviledge priviledge) {
        return priviledgeRepository.save(priviledge);
    }

    public Priviledge updatePriviledge(Long id, Priviledge priviledgeDetails) {
        return priviledgeRepository.findById(id).map(priviledge -> {
            priviledge.setPriviledge(priviledgeDetails.getPriviledge());
            return priviledgeRepository.save(priviledge);
        }).orElseThrow(() -> new RuntimeException("Priviledge not found with id " + id));
    }

    public void deletePriviledge(Long id) {
        priviledgeRepository.deleteById(id);
    }
}
