package com.myresume.myresume.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myresume.myresume.entity.InterViewEntity;
import com.myresume.myresume.entity.StatusEntity;
import com.myresume.myresume.entity.UersGeneralEntity;
import com.myresume.myresume.reponse.InterviewResponse;
import com.myresume.myresume.reponse.StatusReponse;
import com.myresume.myresume.repository.InterViewRepository;

@RestController
@RequestMapping("/api/v4")
public class InterViewController {
    
  @Autowired
  InterViewRepository interviewRepository;

  @GetMapping("/interview")
  public ResponseEntity<InterviewResponse> getStatusBy() {
    List<InterViewEntity> interview = interviewRepository.getInterViewBy();
    return ResponseEntity.ok(new InterviewResponse("ok", "200", interview));
  }


  @GetMapping("/interview/{interviewID}")
  public ResponseEntity<InterviewResponse> getStatusById(@PathVariable Integer interviewID) {
    InterViewEntity interview = interviewRepository
    .findById(interviewID)
    .orElseThrow();

    InterviewResponse response = new InterviewResponse("ok", "200", Collections.singletonList(interview));
    return ResponseEntity.ok(response);
 }


 @PostMapping("/interview")
  public InterViewEntity createInterview(@RequestBody InterViewEntity interview)
    throws Exception {
    interview.setActive(true);

    return interviewRepository.save(interview);
  }

  @PutMapping("/interview/{interviewID}")
  public ResponseEntity<InterviewResponse> updateInterViewBy(
    @PathVariable Integer interviewID,
    @RequestBody InterViewEntity interviewEdit
  ) {
    InterViewEntity interview = interviewRepository
      .findById(interviewID)
      .orElseThrow();
    interview.setInterview_date(interviewEdit.getInterview_date());
    interview.setInterview_duration(interviewEdit.getInterview_duration());
    interview.setInterview_link(interviewEdit.getInterview_link());
    interview.setInterview_remark(interviewEdit.getInterview_remark());
    interview.setInterview_time(interviewEdit.getInterview_time());

    InterViewEntity updateInterViewBy = interviewRepository.save(interview);
    InterviewResponse response = new InterviewResponse("ok", "200", Collections.singletonList(updateInterViewBy));
    return ResponseEntity.ok(response);
  }


  @DeleteMapping("/interview/{interviewID}")
  public ResponseEntity<Map<String, Boolean>> deleteInterViewBy(
    @PathVariable Integer interviewID
  ) {
    InterViewEntity interview = interviewRepository
      .findById(interviewID)
      .orElseThrow();
    interviewRepository.delete(interview);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return ResponseEntity.ok(response);
  }



}
