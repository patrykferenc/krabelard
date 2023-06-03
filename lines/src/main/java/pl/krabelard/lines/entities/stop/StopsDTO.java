package pl.krabelard.lines.entities.stop;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StopsDTO {
    private List<StopDTO> stops;
}
