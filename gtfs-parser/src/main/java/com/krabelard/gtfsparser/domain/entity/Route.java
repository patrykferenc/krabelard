package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "route")
@Getter
@Setter
public class Route {

  @Id
  @Column(name = "route_id")
  int routeId;

  @Column(name = "route_short_name")
  String routeShortName;

  @Column(name = "route_long_name")
  String longRouteName;
  @Column(name = "route_type")
  int routeType;

  @Column(name = "route_sort_order")
  int routeSortOrder;

}
