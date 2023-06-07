package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.*;
import java.util.Set;
import lombok.*;

@Entity
@Table(name = "trip")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

	@Id
	@Column(name = "trip_id")
	private String tripId;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "route_id")
	private Route routeId;

	@Column(name = "head_sign")
	private String headSign;

	@Column(name = "direction_id")
	private Direction directionId;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

		public static Direction of(int id) {
			return Direction.values()[id];
		}
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
