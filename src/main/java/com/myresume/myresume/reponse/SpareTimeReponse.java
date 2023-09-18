package com.myresume.myresume.reponse;

import com.myresume.myresume.entity.SparetimeEntity;
import java.util.List;
import lombok.Data;

@Data
public class SpareTimeReponse {

  private String message;
  private String status;
  private List<SparetimeEntity> data;

  public SpareTimeReponse(
    String message,
    String status,
    List<SparetimeEntity> data
  ) {
    this.message = message;
    this.status = status;
    this.data = data;
  }
}
