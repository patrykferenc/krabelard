package pl.krabelard.lines.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.krabelard.lines.entities.LineDTO;
import pl.krabelard.lines.entities.LinesDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LineService {

    public static LinesDTO getAllLines() {
        return null;
    }

    public static LinesDTO getAllLinesMocked() {
        return new LinesDTO(List.of(
            new LineDTO("M1"),
            new LineDTO("M2"),
            new LineDTO("1"),
            new LineDTO("2"),
            new LineDTO("3"),
            new LineDTO("4"),
            new LineDTO("5"),
            new LineDTO("6"),
            new LineDTO("9"),
            new LineDTO("10"),
            new LineDTO("11")));
    }
}
