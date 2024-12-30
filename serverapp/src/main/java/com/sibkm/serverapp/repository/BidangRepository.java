package com.sibkm.serverapp.repository;

import com.sibkm.serverapp.entity.Bidang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidangRepository extends JpaRepository<Bidang, Long> {
}
