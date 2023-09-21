package com.myresume.myresume.controller;

import com.myresume.myresume.entity.UersGeneralEntity;
import com.myresume.myresume.reponse.AuthenticationResponse;
import com.myresume.myresume.repository.UserGenRepository;
import com.myresume.myresume.security.JwtTokenProvider;
import com.myresume.myresume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

  @Autowired
  UserService userService;

  @Autowired
  private JwtTokenProvider tokenProvider;

  @Autowired
  private UserGenRepository userGenRepository;

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> loginUsers(
    @RequestBody UersGeneralEntity loginRequest
  ) {
    UersGeneralEntity user = userGenRepository.findByUsersGenPhone(
      loginRequest.getUsersGenPhone()
    );

    boolean loginSuccess = userService.login(
      loginRequest.getUsersGenPhone(),
      loginRequest.getUsersGenPassword()
    );

    if (loginSuccess) {
      String token = tokenProvider.generateToken(
        loginRequest.getUsersGenPhone(),
        user
      );
      return ResponseEntity.ok(
        new AuthenticationResponse("ok", "Login successful", token)
      );
    } else {
      return ResponseEntity.ok(
        new AuthenticationResponse(
          "error",
          "phone or password is incorrect!",
          null
        )
      );
    }
  }
}
