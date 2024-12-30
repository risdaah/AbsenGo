package com.sibkm.serverapp.service;

import com.sibkm.serverapp.entity.DetailMbkm;
import com.sibkm.serverapp.repository.DetailMbkmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailMbkmService {
    @Autowired
    private DetailMbkmRepository detailMbkmRepository;

    public List<DetailMbkm> getAllDetailMbkms() {
        return detailMbkmRepository.findAll();
    }

    public Optional<DetailMbkm> getDetailMbkmById(Long id) {
        return detailMbkmRepository.findById(id);
    }

    public DetailMbkm saveDetailMbkm(DetailMbkm detailMbkm) {
        return detailMbkmRepository.save(detailMbkm);
    }

    public DetailMbkm updateDetailMbkm(Long id, DetailMbkm detailMbkmDetails) {
        return detailMbkmRepository.findById(id).map(detailMbkm -> {
            detailMbkm.setJenis(detailMbkmDetails.getJenis());
            detailMbkm.setJurusan(detailMbkmDetails.getJurusan());
            detailMbkm.setKampus(detailMbkmDetails.getKampus());
            return detailMbkmRepository.save(detailMbkm);
        }).orElseThrow(() -> new RuntimeException("DetailMbkm not found with id " + id));
    }

    public void deleteDetailMbkm(Long id) {
        detailMbkmRepository.deleteById(id);
    }
}
