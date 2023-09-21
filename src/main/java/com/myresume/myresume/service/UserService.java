package com.myresume.myresume.service;

import com.myresume.myresume.entity.UersGeneralEntity;
import com.myresume.myresume.repository.UserGenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserGenRepository userGenRepository;

  public boolean login(String phone, String password) {
    UersGeneralEntity user = userGenRepository.findByUsersGenPhone(phone);
    if (user != null) {
      return passwordEncoder.matches(password, user.getUsersGenPassword());
    }
    return false;
  }
}
