package com.myresume.myresume.entity;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "interview")
public class InterViewEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer interview_id;

  @Column(length = 50)
  private String interview_date;

  @Column(length = 50)
  private String interview_time;

  @Column(length = 255)
  private String interview_link;

  @Column(length = 50)
  private String interview_duration;

  @Column(length = 50)
  private String interview_remark;

  @Column(length = 50)
  private LocalDateTime createAt;

  @Column(length = 50)
  private LocalDateTime updateAt;

  @Column(length = 1)
  private boolean isActive;

  @JsonIgnore
  @OneToMany(mappedBy = "userJob_id", fetch = FetchType.LAZY)
  private List<UsersJobEntity> userJob_id;

  @JsonIgnore
  @OneToMany(mappedBy = "status_id", fetch = FetchType.LAZY)
  private List<StatusEntity> status_id;
}
