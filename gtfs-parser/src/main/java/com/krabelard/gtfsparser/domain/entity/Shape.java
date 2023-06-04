package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.*;
import java.util.Set;
import lombok.*;

@Entity
@Table(name = "shape")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shape {

	@Id
	@Column(name = "shape_id")
	private String shapeId;

	@Column(name = "pt_sequence")
	private int ptSequence;

	@Column(name = "distance_travelled")
	private double distanceTravelled;

	@Column(name = "pt_latitude")
	private double ptLatitude;

	@Column(name = "pt_longitude")
	private double ptLongitude;

	@OneToMany(mappedBy = "shapeId")
	private Set<Trip> trips;
}
