package pl.krabelard.lines.entities.line;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LinesDTO {

	private List<LineDTO> lines;
}
