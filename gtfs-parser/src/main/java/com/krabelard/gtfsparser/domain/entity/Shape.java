package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
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
