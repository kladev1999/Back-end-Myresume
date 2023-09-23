package com.myresume.myresume.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import com.myresume.myresume.entity.InterViewEntity;


public interface InterViewRepository  
    extends JpaRepository<InterViewEntity, Integer>{
    @Query(
    value = "SELECT * FROM interview ",
    nativeQuery = true
    )
    List<InterViewEntity> getInterViewBy();
    
}