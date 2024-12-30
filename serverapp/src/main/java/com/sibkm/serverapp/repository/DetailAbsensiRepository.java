package com.sibkm.serverapp.repository;

import com.sibkm.serverapp.entity.DetailAbsensi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetailAbsensiRepository extends JpaRepository<DetailAbsensi, Long> {

    @Query("SELECT d FROM DetailAbsensi d WHERE d.user.id_user = :userId")
    List<DetailAbsensi> findByUserId(@Param("userId") Long userId);

    @Query("SELECT da FROM DetailAbsensi da JOIN da.user u WHERE u.username = :username")
    List<DetailAbsensi> findByUsername(@Param("username") String username);
}