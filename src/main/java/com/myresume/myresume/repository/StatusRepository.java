package com.myresume.myresume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myresume.myresume.entity.StatusEntity;

public interface StatusRepository 
    extends JpaRepository<StatusEntity, Integer>{
    @Query(
    value = "SELECT * FROM status ",
    nativeQuery = true
    )
    List<StatusEntity> getStatusBy();
    
}
