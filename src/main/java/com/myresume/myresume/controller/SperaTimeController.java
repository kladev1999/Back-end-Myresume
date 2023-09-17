package com.myresume.myresume.controller;

import com.myresume.myresume.entity.SparetimeEntity;
import com.myresume.myresume.repository.SparetimeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
  public List<SparetimeEntity> getSpareTime() {
    return sparetimeRepository.findAll();
  }

  @GetMapping("/sparetimeForUserJob/{userJob_id}")
  public List<SparetimeEntity> getSparetimeForUserJob(
    @PathVariable int userJob_id
  ) {
    return sparetimeRepository.SparetimeForUserJob(userJob_id);
  }

  @PostMapping("/sparetime")
  public SparetimeEntity createUsersJob(@RequestBody SparetimeEntity sparetime)
    throws Exception {
    sparetime.setSpareTime_status(true);

    return sparetimeRepository.save(sparetime);
  }
}
