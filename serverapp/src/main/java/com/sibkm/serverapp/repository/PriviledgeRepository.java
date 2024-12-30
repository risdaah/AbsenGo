package com.sibkm.serverapp.repository;

import com.sibkm.serverapp.entity.Priviledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriviledgeRepository extends JpaRepository<Priviledge, Long> {
}
