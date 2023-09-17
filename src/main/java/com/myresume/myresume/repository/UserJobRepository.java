package com.myresume.myresume.repository;

import com.myresume.myresume.entity.UsersJobEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserJobRepository
  extends JpaRepository<UsersJobEntity, Integer> {
  @Query(
    value = "SELECT * FROM uers_job WHERE is_active = TRUE",
    nativeQuery = true
  )
  List<UsersJobEntity> getUsersJob();
}
