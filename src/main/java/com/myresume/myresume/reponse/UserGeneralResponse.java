package com.myresume.myresume.reponse;

import com.myresume.myresume.entity.UersGeneralEntity;
import lombok.Data;

@Data
public class UserGeneralResponse {

  private String message;
  private String status;
  private UersGeneralEntity data;

  public UserGeneralResponse(
    String message,
    String status,
    UersGeneralEntity data2
  ) {
    this.message = message;
    this.status = status;
    this.data = data2;
  }
}
