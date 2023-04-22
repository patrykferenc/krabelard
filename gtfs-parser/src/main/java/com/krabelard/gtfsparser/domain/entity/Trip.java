package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "trip")
@Getter
@Setter
public class Trip {

    @Id
    @GeneratedValue
    private long id;

    // TODO #KRB-31 configure foreign key referencing Route
    @ManyToOne
    private Route routeId;

    // TODO #KRB-31 configure foreign key referencing <<something>>
    @Column(name = "service_id")
    private long serviceId;

    @Column(name = "trip_id")
    private long gtfsId;

    @Column(name = "head_sign")
    private String headSign;

    @Column(name = "direction_id")
    private Direction directionId;

    // TODO #KRB-31 configure foreign key referencing Shape
    @ManyToOne
    private Shape shapeId;

    // TODO #KRB-31 'exceptional' custom GTFS property

    @Column(name = "wheelchair_accessible")
    private Accessibility wheelchairAccessible;

    @Column(name = "bike_allowed")
    private Accessibility bikesAllowed;

    public enum Direction {
        OUTBOUND,
        INBOUND
    }

    public enum Accessibility {
        NO_INFO,
        AT_LEAST_ONE,
        NOT_ALLOWED
    }
}
