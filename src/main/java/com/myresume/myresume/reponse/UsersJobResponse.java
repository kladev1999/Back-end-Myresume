package com.myresume.myresume.reponse;

import com.myresume.myresume.entity.UsersJobEntity;
import lombok.Data;

@Data
public class UsersJobResponse {

  private String message;
  private String status;
  private UsersJobEntity data;

  public UsersJobResponse(String message, String status, UsersJobEntity data) {
    this.message = message;
    this.status = status;
    this.data = data;
  }
}
