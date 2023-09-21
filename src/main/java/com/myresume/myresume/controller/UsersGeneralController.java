package com.myresume.myresume.controller;

import com.myresume.myresume.config.SecurityConfig;
import com.myresume.myresume.entity.UersGeneralEntity;
import com.myresume.myresume.reponse.UserGeneralListReponse;
import com.myresume.myresume.reponse.UserGeneralResponse;
import com.myresume.myresume.repository.UserGenRepository;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class UsersGeneralController {

  @Autowired
  UserGenRepository userRepository;

  @Autowired
  SecurityConfig securityConfig;

  @GetMapping("/usersGenaral")
  public ResponseEntity<UserGeneralListReponse> getUsersGeneral() {
    List<UersGeneralEntity> data = userRepository.getUsersGeneral();

    return ResponseEntity
      .status(HttpStatus.UNAUTHORIZED)
      .body(new UserGeneralListReponse("ok", "200", data));
  }

  @GetMapping("/usersGenaral/{usersGeneralID}")
  public ResponseEntity<UserGeneralResponse> getUsersGenaralById(
    @PathVariable Integer usersGeneralID
  ) {
    UersGeneralEntity uersGeneral = userRepository
      .findById(usersGeneralID)
      .orElseThrow();
    return ResponseEntity.ok(new UserGeneralResponse("ok", "200", uersGeneral));
  }

  @PostMapping("/usersGenaral")
  public ResponseEntity<UserGeneralResponse> createUsersGenaral(
    @RequestBody UersGeneralEntity usersGeneral
  ) throws Exception {
    BCryptPasswordEncoder passwordEncoder = securityConfig.passwordEncoder();

    String hashedPassword = passwordEncoder.encode(
      usersGeneral.getUsersPassword()
    );
    usersGeneral.setUsersPassword(hashedPassword);
    usersGeneral.setActive(true);
    usersGeneral.setCreateAt(LocalDateTime.now());

    UersGeneralEntity data = userRepository.save(usersGeneral);

    return ResponseEntity.ok(new UserGeneralResponse("ok", "200", data));
  }

  @PutMapping("/usersGenaral/{usersGeneralID}")
  public ResponseEntity<UserGeneralResponse> updateUersGeneral(
    @PathVariable Integer usersGeneralID,
    @RequestBody UersGeneralEntity usersGeneralEdit
  ) {
    UersGeneralEntity usersGen = userRepository
      .findById(usersGeneralID)
      .orElseThrow();
    usersGen.setUpdateAt(LocalDateTime.now());
    usersGen.setUserImg(usersGeneralEdit.getUserImg());
    usersGen.setUserFirstName(usersGeneralEdit.getUserFirstName());
    usersGen.setUserLastName(usersGeneralEdit.getUserLastName());
    usersGen.setUsersCompanyName(usersGeneralEdit.getUsersCompanyName());
    usersGen.setUsersPhone(usersGeneralEdit.getUsersPhone());
    usersGen.setUsersEmail(usersGeneralEdit.getUsersEmail());

    UersGeneralEntity updateUersGeneral = userRepository.save(usersGen);
    return ResponseEntity.ok(
      new UserGeneralResponse("ok", "200", updateUersGeneral)
    );
  }

  @PutMapping("/RemoveUersGeneral/{usersGeneralID}")
  public ResponseEntity<Map<String, Boolean>> RemoveUersGeneral(
    @PathVariable Integer usersGeneralID
  ) {
    UersGeneralEntity usersGen = userRepository
      .findById(usersGeneralID)
      .orElseThrow();
    usersGen.setActive(false);

    userRepository.save(usersGen);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/usersGenaral/{usersGeneralID}")
  public ResponseEntity<Map<String, Boolean>> deleteUsersGenaral(
    @PathVariable Integer usersGeneralID
  ) {
    UersGeneralEntity usersGenaral = userRepository
      .findById(usersGeneralID)
      .orElseThrow();
    userRepository.delete(usersGenaral);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return ResponseEntity.ok(response);
  }
}
