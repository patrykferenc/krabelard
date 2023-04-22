package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

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
