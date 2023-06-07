package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "frequency")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Frequency {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL
	)
	@JoinColumn(name = "trip_id")
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
		ScheduleBasedTrips;

		public static ExactTimes of(int id) {
			return ExactTimes.values()[id];
		}
	}
}
