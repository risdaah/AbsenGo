package com.sibkm.serverapp.service;

import com.sibkm.serverapp.entity.DetailUser;
import com.sibkm.serverapp.repository.DetailUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailUserService {
    @Autowired
    private DetailUserRepository detailUserRepository;

    public List<DetailUser> getAllDetailUsers() {
        return detailUserRepository.findAll();
    }

    public Optional<DetailUser> getDetailUserById(Long id) {
        return detailUserRepository.findById(id);
    }

    public DetailUser saveDetailUser(DetailUser detailUser) {
        return detailUserRepository.save(detailUser);
    }

    public DetailUser updateDetailUser(Long id, DetailUser detailUserDetails) {
        return detailUserRepository.findById(id).map(detailUser -> {
            detailUser.setNamaDepan(detailUserDetails.getNamaDepan());
            detailUser.setNamaBelakang(detailUserDetails.getNamaBelakang());
            return detailUserRepository.save(detailUser);
        }).orElseThrow(() -> new RuntimeException("DetailUser not found with id " + id));
    }

    public void deleteDetailUser(Long id) {
        detailUserRepository.deleteById(id);
    }

    // public List<DetailUser> getDetailUsersWithRoleId2() {
    // return detailUserRepository.findDetailUsersByRoleId2();
    // }
}
