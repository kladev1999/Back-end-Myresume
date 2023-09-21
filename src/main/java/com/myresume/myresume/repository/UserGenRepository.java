package com.myresume.myresume.repository;

import com.myresume.myresume.entity.UersGeneralEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserGenRepository
  extends JpaRepository<UersGeneralEntity, Integer> {
  @Query(
    value = "SELECT * FROM uers_general WHERE is_active = TRUE",
    nativeQuery = true
  )
  List<UersGeneralEntity> getUsersGeneral();

  UersGeneralEntity findByUsersGenPhone(String usersGenPhone);
}
