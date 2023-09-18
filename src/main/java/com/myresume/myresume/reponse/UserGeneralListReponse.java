package com.myresume.myresume.reponse;

import com.myresume.myresume.entity.UersGeneralEntity;
import java.util.List;
import lombok.Data;

@Data
public class UserGeneralListReponse {

  private String message;
  private String status;
  private List<UersGeneralEntity> data;

  public UserGeneralListReponse(
    String message,
    String status,
    List<UersGeneralEntity> data
  ) {
    this.message = message;
    this.status = status;
    this.data = data;
  }
}
