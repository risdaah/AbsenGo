package com.sibkm.serverapp.service;

import com.sibkm.serverapp.entity.Absensi;
import com.sibkm.serverapp.repository.AbsensiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbsensiService {
    @Autowired
    private AbsensiRepository absensiRepository;

    public List<Absensi> getAllAbsensi() {
        return absensiRepository.findAll();
    }

    public Optional<Absensi> getAbsensiById(Long id) {
        return absensiRepository.findById(id);
    }

    public Absensi saveAbsensi(Absensi absensi) {
        return absensiRepository.save(absensi);
    }

    public Absensi updateAbsensi(Long id, Absensi absensiDetails) {
        return absensiRepository.findById(id).map(absensi -> {
            absensi.setTanggal(absensiDetails.getTanggal());
            return absensiRepository.save(absensi);
        }).orElseThrow(() -> new RuntimeException("Absensi not found with id " + id));
    }

    public void deleteAbsensi(Long id) {
        absensiRepository.deleteById(id);
    }
}
