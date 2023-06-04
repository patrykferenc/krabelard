package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "stop")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stop {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "stop_id")
	private String gtfsId;

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
