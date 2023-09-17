package com.myresume.myresume.repository;

import com.myresume.myresume.entity.SparetimeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SparetimeRepository
  extends JpaRepository<SparetimeEntity, Integer> {
  @Query(
    value = "SELECT * FROM spare_time WHERE user_job_id = :userJob_id",
    nativeQuery = true
  )
  List<SparetimeEntity> SparetimeForUserJob(
    @Param("userJob_id") Integer userJob_id
  );
}
