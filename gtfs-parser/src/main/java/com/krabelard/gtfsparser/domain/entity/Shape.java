package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "shape")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shape {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "gtfs_id")
	private String gtfsId;

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
