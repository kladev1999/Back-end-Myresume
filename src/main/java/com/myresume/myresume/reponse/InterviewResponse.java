package com.myresume.myresume.reponse;

import java.util.List;

import com.myresume.myresume.entity.InterViewEntity;

import lombok.Data;

@Data
public class InterviewResponse {
    
  private String message;
  private String status;
  private List<InterViewEntity> data;

  public InterviewResponse(
    String message,
    String status,
    List<InterViewEntity> data
  ) {
    this.message = message;
    this.status = status;
    this.data = data;
  }
}
