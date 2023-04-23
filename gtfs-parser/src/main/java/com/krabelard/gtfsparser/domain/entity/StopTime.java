package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.*;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stop_time")
@Getter
@Setter
public class StopTime {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Trip tripId;

	@Column(name = "arrival_time")
	private LocalTime arrivalTime;

	@Column(name = "departure_time")
	private LocalTime departureTime;

	@ManyToOne(fetch = FetchType.LAZY)
	private Stop stopId;

	@Column(name = "stop_sequence")
	private int stopSequence;

	@Column(name = "pickup_type")
	@Enumerated(EnumType.ORDINAL)
	private PickupType pickupType;

	@Column(name = "dropoff_type")
	@Enumerated(EnumType.ORDINAL)
	private PickupType dropoffType;

	@Column(name = "shape_dist_travelled")
	private double shapeDistTravelled;

	public enum PickupType {
		REGULAR,
		NOT_AVAILABLE,
		MUST_PHONE_AGENCY,
		MUST_COORDINATE_WITH_DRIVER,
	}
}
