package com.myresume.myresume.controller;

import com.myresume.myresume.entity.SparetimeEntity;
import com.myresume.myresume.reponse.SpareTimeReponse;
import com.myresume.myresume.repository.SparetimeRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3")
public class SperaTimeController {

  @Autowired
  SparetimeRepository sparetimeRepository;

  @GetMapping("/sparetime")
  public ResponseEntity<SpareTimeReponse> getSpareTime() {
    List<SparetimeEntity> data = sparetimeRepository.findAll();

    //return ResponseEntity.ok(new SpareTimeReponse("ok", "200", data));

    return ResponseEntity
      .status(HttpStatus.UNAUTHORIZED)
      .body(new SpareTimeReponse("ok", "200", data));
  }

  @GetMapping("/sparetimeForUserJob/{userJob_id}")
  public ResponseEntity<SpareTimeReponse> getSparetimeForUserJob(
    @PathVariable int userJob_id
  ) {
    List<SparetimeEntity> data = sparetimeRepository.SparetimeForUserJob(
      userJob_id
    );

    return ResponseEntity.ok(new SpareTimeReponse("ok", "200", data));
  }

  @PostMapping("/sparetime")
  public ResponseEntity<Map<String, String>> createSparetimeForUserJob(
    @RequestBody SparetimeEntity sparetime
  ) throws Exception {
    sparetime.setSpareTime_status(true);
    sparetimeRepository.save(sparetime);
    Map<String, String> response = new HashMap<>();
    response.put("message", "successfully");
    return ResponseEntity.ok(response);
  }
}
