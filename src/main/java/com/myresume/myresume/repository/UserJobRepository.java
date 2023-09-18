package com.myresume.myresume.repository;

import com.myresume.myresume.entity.UsersJobEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserJobRepository
  extends JpaRepository<UsersJobEntity, Integer> {
  @Query(
    value = "SELECT * FROM uers_job WHERE is_active = TRUE",
    nativeQuery = true
  )
  List<UsersJobEntity> getUsersJob();

  @Query(
    value = "SELECT * FROM uers_job uj WHERE user_job_first_name LIKE %:usersJobName% ",
    nativeQuery = true
  )
  List<UsersJobEntity> getByName(@Param("usersJobName") String usersJobName);
}
