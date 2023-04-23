package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.*;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shape")
@Getter
@Setter
public class Shape {

	@Id
	@GeneratedValue
	private long id;

	private long gtfsId;

	private int ptSequence;

	private double distanceTravelled;

	private double ptLatitude;

	private double ptLongitude;

	@OneToMany(mappedBy = "shapeId")
	private Set<Trip> trips;
}
