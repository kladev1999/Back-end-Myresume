package com.myresume.myresume.controller;

import com.myresume.myresume.entity.UersGeneralEntity;
import com.myresume.myresume.repository.UserGenRepository;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UsersGeneralController {

  @Autowired
  UserGenRepository userRepository;

  @GetMapping("/usersGenaral")
  public List<UersGeneralEntity> getUsersGeneral() {
    return userRepository.getUsersGeneral();
  }

  @GetMapping("/usersGenaral/{usersGeneralID}")
  public ResponseEntity<UersGeneralEntity> getUsersGenaralById(
    @PathVariable Integer usersGeneralID
  ) {
    UersGeneralEntity uersGeneral = userRepository
      .findById(usersGeneralID)
      .orElseThrow();
    return ResponseEntity.ok(uersGeneral);
  }

  @PostMapping("/usersGenaral")
  public UersGeneralEntity createUsersGenaral(
    @RequestBody UersGeneralEntity usersGeneral
  ) throws Exception {
    usersGeneral.setUserGenneralRole(true);
    usersGeneral.setActive(true);
    usersGeneral.setCreateAt(LocalDateTime.now());

    return userRepository.save(usersGeneral);
  }

  @PutMapping("/usersGenaral/{usersGeneralID}")
  public ResponseEntity<UersGeneralEntity> updateUersGeneral(
    @PathVariable Integer usersGeneralID,
    @RequestBody UersGeneralEntity usersGeneralEdit
  ) {
    UersGeneralEntity usersGen = userRepository
      .findById(usersGeneralID)
      .orElseThrow();
    usersGen.setUpdateAt(LocalDateTime.now());
    usersGen.setUserGenFirstName(usersGeneralEdit.getUserGenFirstName());
    usersGen.setUserGenLastName(usersGeneralEdit.getUserGenLastName());
    usersGen.setUsersGenCompanyName(usersGeneralEdit.getUsersGenCompanyName());
    usersGen.setUsersGenPhone(usersGeneralEdit.getUsersGenPhone());
    usersGen.setUsersGenEmail(usersGeneralEdit.getUsersGenEmail());

    UersGeneralEntity updateUersGeneral = userRepository.save(usersGen);
    return ResponseEntity.ok(updateUersGeneral);
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
