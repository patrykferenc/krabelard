package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.*;
import java.util.Set;
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

	@ManyToOne(fetch = FetchType.LAZY)
	private Route routeId;

	@Column(name = "trip_id")
	private String tripId;

	@Column(name = "head_sign")
	private String headSign;

	@Column(name = "direction_id")
	private Direction directionId;

	@ManyToOne(fetch = FetchType.LAZY)
	private Shape shapeId;

	@Column(name = "wheelchair_accessible")
	private Accessibility wheelchairAccessible;

	@Column(name = "bike_allowed")
	private Accessibility bikesAllowed;

	@OneToMany(mappedBy = "tripId")
	private Set<StopTime> stopTimes;

	@OneToMany(mappedBy = "serviceId")
	private Set<CalendarDates> dates;

	@OneToMany(mappedBy = "tripId")
	private Set<Frequencies> frequencies;

	public enum Direction {
		OUTBOUND,
		INBOUND,
	}

	public enum Accessibility {
		NO_INFO,
		AT_LEAST_ONE,
		NOT_ALLOWED,
	}
}
