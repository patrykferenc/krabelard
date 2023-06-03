package pl.krabelard.lines.entities.line;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LinesDTO {
    private List<LineDTO> lines;
}
