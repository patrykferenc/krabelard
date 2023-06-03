package pl.krabelard.lines.entities.stop;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StopsDTO {

	private List<StopDTO> stops;
}
