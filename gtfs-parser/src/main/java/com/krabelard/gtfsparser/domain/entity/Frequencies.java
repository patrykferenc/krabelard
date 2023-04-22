package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
  private String tripId;
  @Column(name = "start_time")
  private LocalTime startTime;
  @Column(name = "end_time")
  private LocalTime endTime;
  @Column(name = "headway_secs")
  int headwaySecs;
  @Column(name = "exact_times")
  @Enumerated(EnumType.ORDINAL)
  private ExactTimes exactTimes;

  private enum ExactTimes {
    FrequencyBasedTrips,
    ScheduleBasedTrips
  }
}
