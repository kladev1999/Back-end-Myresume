package com.myresume.myresume.controller;

import com.myresume.myresume.entity.UsersJobEntity;
import com.myresume.myresume.repository.UserJobRepository;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/")
public class UsersJobController {

  @Autowired
  UserJobRepository userJobRepository;

  @GetMapping("/usersJob")
  public List<UsersJobEntity> getAllUsersJob() {
    return userJobRepository.getUsersJob();
  }

  @GetMapping("/usersJob/{usersJobID}")
  public ResponseEntity<UsersJobEntity> getUsersJobById(
    @PathVariable Integer usersJobID
  ) {
    UsersJobEntity uersJob = userJobRepository
      .findById(usersJobID)
      .orElseThrow();
    return ResponseEntity.ok(uersJob);
  }

  @PostMapping("/usersJob")
  public UsersJobEntity createUsersJob(@RequestBody UsersJobEntity userjob)
    throws Exception {
    userjob.setUserJobRole(true);
    userjob.setActive(true);
    userjob.setCreateAt(LocalDateTime.now());

    return userJobRepository.save(userjob);
  }

  @PutMapping("/usersJob/{usersJobID}")
  public ResponseEntity<UsersJobEntity> updateUersJob(
    @PathVariable Integer usersJobID,
    @RequestBody UsersJobEntity usersJobEdit
  ) {
    UsersJobEntity usersJob = userJobRepository
      .findById(usersJobID)
      .orElseThrow();
    usersJob.setUpdateAt(LocalDateTime.now());
    usersJob.setUserJobImg(usersJobEdit.getUserJobImg());
    usersJob.setUserJobFirstName(usersJobEdit.getUserJobFirstName());
    usersJob.setUserJobLastName(usersJobEdit.getUserJobLastName());
    usersJob.setUsersJobPhone(usersJobEdit.getUsersJobPhone());
    usersJob.setUsersJobEmail(usersJobEdit.getUsersJobEmail());

    UsersJobEntity updateUersJob = userJobRepository.save(usersJob);
    return ResponseEntity.ok(updateUersJob);
  }

  @PutMapping("/RemoveusersJob/{usersJobID}")
  public ResponseEntity<Map<String, String>> RemoveUersJob(
    @PathVariable Integer usersJobID
  ) {
    UsersJobEntity usersGen = userJobRepository
      .findById(usersJobID)
      .orElseThrow();
    usersGen.setActive(false);

    userJobRepository.save(usersGen);
    Map<String, String> response = new HashMap<>();
    response.put("deleted", "Successfully");
    return ResponseEntity.ok(response);
  }
}
