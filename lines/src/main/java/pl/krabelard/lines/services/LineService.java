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
            new LineDTO("6"),
            new LineDTO("7"),
            new LineDTO("9"),
            new LineDTO("11"),
            new LineDTO("13"),
            new LineDTO("15"),
            new LineDTO("17"),
            new LineDTO("20"),
            new LineDTO("22"),
            new LineDTO("23"),
            new LineDTO("24"),
            new LineDTO("25"),
            new LineDTO("26"),
            new LineDTO("27"),
            new LineDTO("102"),
            new LineDTO("103"),
            new LineDTO("105"),
            new LineDTO("106"),
            new LineDTO("107"),
            new LineDTO("108"),
            new LineDTO("109"),
            new LineDTO("110"),
            new LineDTO("111"),
            new LineDTO("112"),
            new LineDTO("120")));
    }
}
