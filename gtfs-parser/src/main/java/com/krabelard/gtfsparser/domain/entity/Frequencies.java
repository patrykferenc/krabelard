package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.*;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "frequencies")
@Getter
@Setter
public class Frequencies {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private Trip tripId;

	@Column(name = "start_time")
	private LocalTime startTime;

	@Column(name = "end_time")
	private LocalTime endTime;

	@Column(name = "headway_secs")
	int headwaySecs;

	@Column(name = "exact_times")
	@Enumerated(EnumType.ORDINAL)
	private ExactTimes exactTimes;

	public enum ExactTimes {
		FrequencyBasedTrips,
		ScheduleBasedTrips,
	}
}
