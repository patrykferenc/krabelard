package pl.krabelard.lines.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    private String routeId;

    private String routeShortName;
    private String routeLongName;

    private String routeType;
    private String routeColor;

    private String routeTextColor;

}
