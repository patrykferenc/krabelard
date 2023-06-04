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
@Table(name = "frequency")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Frequency {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
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
