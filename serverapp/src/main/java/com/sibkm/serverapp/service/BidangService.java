package com.sibkm.serverapp.service;

import com.sibkm.serverapp.entity.Bidang;
import com.sibkm.serverapp.repository.BidangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidangService {
    @Autowired
    private BidangRepository bidangRepository;

    public List<Bidang> getAllBidangs() {
        return bidangRepository.findAll();
    }

    public Optional<Bidang> getBidangById(Long id) {
        return bidangRepository.findById(id);
    }

    public Bidang saveBidang(Bidang bidang) {
        return bidangRepository.save(bidang);
    }

    public Bidang updateBidang(Long id, Bidang bidangDetails) {
        return bidangRepository.findById(id).map(bidang -> {
            bidang.setNama_bidang(bidangDetails.getNama_bidang());
            return bidangRepository.save(bidang);
        }).orElseThrow(() -> new RuntimeException("Bidang not found with id " + id));
    }

    public void deleteBidang(Long id) {
        bidangRepository.deleteById(id);
    }
}
