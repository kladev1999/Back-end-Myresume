package com.myresume.myresume.reponse;

import lombok.Data;

@Data
public class AuthenticationResponse {

  private String status;
  private String message;
  private String token;

  public AuthenticationResponse(String status, String message, String token) {
    this.status = status;
    this.message = message;
    this.token = token;
  }
}
