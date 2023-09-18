package com.myresume.myresume.reponse;

import com.myresume.myresume.entity.UsersJobEntity;
import java.util.List;
import lombok.Data;

@Data
public class UsersJobListResponse {

  private String message;
  private String status;
  private List<UsersJobEntity> data;

  public UsersJobListResponse(
    String message,
    String status,
    List<UsersJobEntity> data
  ) {
    this.message = message;
    this.status = status;
    this.data = data;
  }
}
