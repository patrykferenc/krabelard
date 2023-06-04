package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.*;
import java.util.Set;
import lombok.*;

@Entity
@Table(name = "stop")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stop {

	@Id
	@Column(name = "stop_id")
	private String stopId;

	@Column(name = "stop_name")
	private String name;

	@Column(name = "stop_lat")
	private double latitude;

	@Column(name = "stop_lon")
	private double longitude;

	@Column(name = "location_type")
	@Enumerated(value = EnumType.ORDINAL)
	private LocationType locationType;

	@Column(name = "zone_id")
	private String zoneId;

	@OneToMany(mappedBy = "stopId")
	Set<StopTime> stopTimes;

	public enum LocationType {
		STOP,
		STATION,
		ENTRANCE_EXIT,
		GENERIC,
		BOARDING_AREA;

		public static LocationType of(int id) {
			return LocationType.values()[id];
		}
	}
}
