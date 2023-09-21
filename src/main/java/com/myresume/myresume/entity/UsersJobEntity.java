package com.myresume.myresume.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "UersJob")
public class UsersJobEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer userJob_id;

  @Column(length = 255)
  private String userJobImg;

  @Column(length = 50)
  private String userJobFirstName;

  @Column(length = 50)
  private String userJobLastName;

  @Column(length = 255)
  private String userJobResumeImg;

  @Column(length = 50)
  private String usersJobPhone;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Column(length = 255)
  private String usersJobPassword;

  @Column(length = 50)
  private String usersJobEmail;

  @Column(length = 1)
  private boolean isUserJobRole;

  @Column(length = 50)
  private LocalDateTime createAt;

  @Column(length = 50)
  private LocalDateTime updateAt;

  @Column(length = 1)
  private boolean isActive;

  @JsonIgnore
  @OneToMany(mappedBy = "spareTime_id", fetch = FetchType.LAZY)
  private List<SparetimeEntity> spareTime_id;
}
