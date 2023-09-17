package com.myresume.myresume.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "spare_time")
public class SparetimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer spareTime_id;

  @Column(length = 100)
  private String spareTime_dateStart;

  @Column(length = 100)
  private String spareTime_dateEnd;

  @Column(length = 100)
  private String spareTime_timeStart;

  @Column(length = 100)
  private String spareTime_timeEnd;

  @Column(length = 1)
  private boolean spareTime_status;

  @ManyToOne
  @JoinColumn(name = "userJob_id")
  private UsersJobEntity userJob_id;
}
