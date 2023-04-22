package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
  @Enumerated(EnumType.ORDINAL)
  RouteType routeType;

  @Column(name = "route_sort_order")
  int routeSortOrder;

  private enum RouteType {
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
    RouteType(int typeId)
    {
      this.typeId = typeId;
    }

    public int getTypeId() {
      return typeId;
    }
  }

}
