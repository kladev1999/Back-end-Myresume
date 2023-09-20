package com.myresume.myresume.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "status")
public class StatusEntity {
    
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer status_id;

  @Column(length = 255)
  private String status_name;

  @Column(length = 1)
  private boolean isActive;
}
