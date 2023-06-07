package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.*;
import java.util.Set;
import lombok.*;

@Entity
@Table(name = "route")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Route {

	@Id
	@Column(name = "route_id")
	private String routeId;

	@Column(name = "route_short_name")
	private String routeShortName;

	@Column(name = "route_long_name")
	private String routeLongName;

	@Column(name = "route_type")
	private Type routeType;

	@Column(name = "route_sort_order")
	private int routeSortOrder;

	@OneToMany(mappedBy = "routeId")
	private Set<Trip> trips;

	public enum Type {
		LightRail,
		UndergroundRail,
		Rail,
		Bus,
		Ferry,
		CableTram,
		AerialLift,
		Funicular,
		Trolleybus,
		Monorail;

		public static Type of(int id) {
			return Type.values()[id];
		}
	}
}
