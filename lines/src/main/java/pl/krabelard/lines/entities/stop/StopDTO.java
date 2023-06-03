package pl.krabelard.lines.entities.stop;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StopDTO {
    private String stopId;
    private String stopName;
    private String stopLat;
    private String stopLon;

}
