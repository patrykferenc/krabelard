package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "stop_time")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StopTime {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trip_id")
	private Trip tripId;

	@Column(name = "arrival_time")
	private LocalTime arrivalTime;

	@Column(name = "departure_time")
	private LocalTime departureTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stop_id")
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
		MUST_COORDINATE_WITH_DRIVER;

		public static PickupType of(int id) {
			return PickupType.values()[id];
		}
	}
}
