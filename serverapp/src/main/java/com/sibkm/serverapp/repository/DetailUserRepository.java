package com.sibkm.serverapp.repository;

import com.sibkm.serverapp.entity.DetailUser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailUserRepository extends JpaRepository<DetailUser, Long> {

}
