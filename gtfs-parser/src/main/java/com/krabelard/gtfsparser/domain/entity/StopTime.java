package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class StopTime {

    @Id
    @GeneratedValue
    private long id;

    // TODO #KRB-31 configure foreign key referencing Trip
    @ManyToOne
    private Trip tripId;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    // TODO #KRB-31 configure foreign key referencing Stop
    @ManyToOne
    private Stop stopId;

    @Column(name = "stop_sequence")
    private int stopSequence;

    @Column(name = "pickup_type")
    private PickupType pickupType;

    @Column(name = "dropoff_type")
    private PickupType dropoffType;

    @Column(name = "shape_dist_travelled")
    private double shapeDistTravelled;

    // TODO - figure out more sensible name
    public enum PickupType {
        REGULAR,
        NOT_AVAILABLE,
        MUST_PHONE_AGENCY,
        MUST_COORDINATE_WITH_DRIVER
    }
}
