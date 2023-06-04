package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "trip")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "route_id")
	private Route routeId;

	@Column(name = "trip_id")
	private String tripId;

	@Column(name = "head_sign")
	private String headSign;

	@Column(name = "direction_id")
	private Direction directionId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shape_id")
	private Shape shapeId;

	@Column(name = "wheelchair_accessible")
	private Accessibility wheelchairAccessible;

	@Column(name = "bike_allowed")
	private Accessibility bikesAllowed;

	@OneToMany(mappedBy = "tripId")
	private Set<StopTime> stopTimes;

	@OneToMany(mappedBy = "serviceId")
	private Set<CalendarDate> dates;

	@OneToMany(mappedBy = "tripId")
	private Set<Frequency> frequencies;

	public enum Direction {
		OUTBOUND,
		INBOUND;
	}

	public enum Accessibility {
		NO_INFO,
		AT_LEAST_ONE,
		NOT_ALLOWED;

		public static Accessibility of(int id) {
			return Accessibility.values()[id];
		}
	}
}
