package pl.krabelard.lines.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DirectionsDTO {
    String directionFrom;
    String directionTo;
}
