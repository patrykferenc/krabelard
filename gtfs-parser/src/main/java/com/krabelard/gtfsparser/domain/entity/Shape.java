package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shape")
@Getter
@Setter
public class Shape {

    @Id
    @GeneratedValue
    private long id;

    private long gtfsId;

    private int ptSequence;

    private double distanceTravelled;

    private double ptLatitude;

    private double ptLongitude;

}
