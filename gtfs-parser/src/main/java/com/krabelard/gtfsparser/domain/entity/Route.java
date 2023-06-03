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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "route_id")
	private long routeId;

	@Column(name = "route_short_name")
	private String routeShortName;

	@Column(name = "route_long_name")
	private String longRouteName;

	@Column(name = "route_type")
	@Enumerated(EnumType.ORDINAL)
	private RouteType routeType;

	@Column(name = "route_sort_order")
	private int routeSortOrder;

	@OneToMany(mappedBy = "routeId")
	private Set<Trip> trips;

	public enum RouteType {
		Tram(0),
		StreetCar(0),
		LightRail(0),
		Subway(1),
		Metro(1),
		Rail(2),
		Bus(3),
		Ferry(4),
		CableTram(5),
		AerialShift(6),
		Funicular(7),
		Trolleybus(11),
		Monorail(12);

		private final int typeId;

		RouteType(int typeId) {
			this.typeId = typeId;
		}

		public int getTypeId() {
			return typeId;
		}

		public static RouteType of(int id) {
			return RouteType.values()[id];
		}
	}
}
