package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.*;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stop")
@Getter
@Setter
public class Stop {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "stop_id")
	private long gtfsId;

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
	private long zoneId;

	@OneToMany(mappedBy = "stopId")
	Set<StopTime> stopTimes;

	public enum LocationType {
		STOP,
		STATION,
		ENTRANCE_EXIT,
		GENERIC,
		BOARDING_AREA,
	}
}
