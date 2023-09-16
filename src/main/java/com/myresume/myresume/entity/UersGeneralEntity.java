package com.myresume.myresume.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "UersGeneral")
public class UersGeneralEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer userGen_id;

  @Column(nullable = false, length = 255)
  private String userGenImg;

  @Column(length = 50)
  private String userGenFirstName;

  @Column(length = 50)
  private String userGenLastName;

  @Column(length = 50)
  private String usersGenCompanyName;

  @Column(length = 50)
  private String usersGenPhone;

  @Column(length = 50)
  private String usersGenPassword;

  @Column(length = 50)
  private String usersGenEmail;

  @Column(length = 50)
  private LocalDateTime createAt;

  @Column(length = 50)
  private LocalDateTime updateAt;

  @Column(length = 1)
  private boolean isActive;
}
