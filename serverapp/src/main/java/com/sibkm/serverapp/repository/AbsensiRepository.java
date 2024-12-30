package com.sibkm.serverapp.repository;

import com.sibkm.serverapp.entity.Absensi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsensiRepository extends JpaRepository<Absensi, Long> {
}
