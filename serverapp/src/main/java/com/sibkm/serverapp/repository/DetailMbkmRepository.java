package com.sibkm.serverapp.repository;

import com.sibkm.serverapp.entity.DetailMbkm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailMbkmRepository extends JpaRepository<DetailMbkm, Long> {
}
