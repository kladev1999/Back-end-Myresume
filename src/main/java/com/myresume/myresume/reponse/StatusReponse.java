package com.myresume.myresume.reponse;

import java.util.List;
import com.myresume.myresume.entity.StatusEntity;

import lombok.Data;

@Data
public class StatusReponse {
  private String message;
  private String status;
  private List<StatusEntity> data;

  public StatusReponse(
    String message,
    String status,
    List<StatusEntity> data
  ) {
    this.message = message;
    this.status = status;
    this.data = data;
  }
}
