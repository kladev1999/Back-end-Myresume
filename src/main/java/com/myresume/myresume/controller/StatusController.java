package com.myresume.myresume.controller;

import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myresume.myresume.repository.StatusRepository;
import com.myresume.myresume.entity.StatusEntity;

import com.myresume.myresume.reponse.StatusReponse;



@RestController
@RequestMapping("/api/v5")
public class StatusController {

  @Autowired
  StatusRepository statusRepository;
    
  @GetMapping("/status")
  public ResponseEntity<StatusReponse> getStatusBy() {
    List<StatusEntity> status = statusRepository.getStatusBy();
    return ResponseEntity.ok(new StatusReponse("ok", "200", status));
  }

  @GetMapping("/status/{statusID}")
  public ResponseEntity<StatusReponse> getStatusById(@PathVariable Integer statusID) {
    StatusEntity status_entity = statusRepository
    .findById(statusID)
    .orElseThrow();

    StatusReponse response = new StatusReponse("ok", "200", Collections.singletonList(status_entity));
    return ResponseEntity.ok(response);
 }


  @PostMapping("/status")
  public StatusEntity createStatus(@RequestBody StatusEntity status)
    throws Exception {
    status.setActive(true);

    return statusRepository.save(status);
  }


  @PutMapping("/status/{statusID}")
  public ResponseEntity<StatusReponse> updateStatusBy(
    @PathVariable Integer statusID,
    @RequestBody StatusEntity statusEdit
  ) {
    StatusEntity status_entity = statusRepository
      .findById(statusID)
      .orElseThrow();
    status_entity.setStatus_name(statusEdit.getStatus_name());

    StatusEntity updateStatusBy = statusRepository.save(status_entity);
    StatusReponse response = new StatusReponse("ok", "200", Collections.singletonList(updateStatusBy));
    return ResponseEntity.ok(response);
  }


  @PutMapping("/Removestatus/{statusID}")
  public ResponseEntity<Map<String, String>> removeStatusBy(
    @PathVariable Integer statusID
  ) {
    StatusEntity status_entity = statusRepository
      .findById(statusID)
      .orElseThrow();
    status_entity.setActive(false);

    statusRepository.save(status_entity);
    Map<String, String> response = new HashMap<>();
    response.put("deleted", "Successfully");
    return ResponseEntity.ok(response);
  }


    
}
