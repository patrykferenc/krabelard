package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "frequencies")
@Getter
@Setter
public class Frequencies {

  @Id
  @GeneratedValue
  int id;

  @Column(name = "trip_id")
  String tripId;
  @Column(name = "start_time")
  LocalTime startTime;
  @Column(name = "end_time")
  LocalTime endTime;
  @Column(name = "headway_secs")
  int headwaySecs;
  @Column(name = "exact_times")
  int exactTimes;

}
