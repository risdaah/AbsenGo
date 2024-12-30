package com.sibkm.serverapp.service;

import com.sibkm.serverapp.entity.*;
import com.sibkm.serverapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailAbsensiService {

    @Autowired
    private DetailAbsensiRepository detailAbsensiRepository;
    private UserRepository userRepository;
    private AbsensiRepository absensiRepository;

    public List<DetailAbsensi> getAllDetailAbsensi() {
        return detailAbsensiRepository.findAll();
    }

    public Optional<DetailAbsensi> getDetailAbsensiById(Long id) {
        return detailAbsensiRepository.findById(id);
    }

    public List<DetailAbsensi> getDetailAbsensiByUsername(String username) {
        return detailAbsensiRepository.findByUsername(username);
    }

    public DetailAbsensi saveDetailAbsensi(DetailAbsensi detailAbsensi) {
        return detailAbsensiRepository.save(detailAbsensi);
    }

    public DetailAbsensi createDetailAbsensi(Long userId, Long absensiId, String keterangan, String jam) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        Absensi absensi = absensiRepository.findById(absensiId)
                .orElseThrow(() -> new IllegalArgumentException("Absensi not found with id: " + absensiId));

        DetailAbsensi detailAbsensi = new DetailAbsensi();
        detailAbsensi.setUser(user);
        detailAbsensi.setAbsensi(absensi);
        detailAbsensi.setKeterangan(keterangan);
        detailAbsensi.setJam(jam);

        return detailAbsensiRepository.save(detailAbsensi);
    }

    public DetailAbsensi updateDetailAbsensi(Long id, DetailAbsensi detailAbsensiDetails) {
        return detailAbsensiRepository.findById(id).map(detailAbsensi -> {
            detailAbsensi.setKeterangan(detailAbsensiDetails.getKeterangan());
            detailAbsensi.setJam(detailAbsensiDetails.getJam());
            return detailAbsensiRepository.save(detailAbsensi);
        }).orElseThrow(() -> new RuntimeException("DetailAbsensi not found with id " + id));
    }

    public void deleteDetailAbsensi(Long id) {
        detailAbsensiRepository.deleteById(id);
    }
}