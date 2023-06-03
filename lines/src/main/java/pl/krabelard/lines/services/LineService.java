package pl.krabelard.lines.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.krabelard.lines.entities.*;

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

    public static DirectionsDTO getDirections(String line) {
        return null;
    }

    public static DirectionsDTO getDirectionsMocked(String line) {
        switch(line){
            case "M1":
                return new DirectionsDTO("Młociny", "Kabaty");
            case "M2":
                return new DirectionsDTO("Dworzec Wileński", "Bemowo");
            case "1":
                return new DirectionsDTO("Kino Femina", "Os. Górczewska");
            case "2":
                return new DirectionsDTO("Dworzec Wileński", "Os. Górczewska");
            case "3":
                return new DirectionsDTO("Pl. Wilsona", "Woronicza");
            case "4":
                return new DirectionsDTO("Dworzec Wileński", "Pl. Wilsona");
            case "6":
                return new DirectionsDTO("Dworzec Wileński", "Rondo Waszyngtona");
            case "7":
                return new DirectionsDTO("Dworzec Wileński", "Pl. Wilsona");
            case "9":
                return new DirectionsDTO("Dworzec Wileński", "Rondo Waszyngtona");
            case "11":
                return new DirectionsDTO("Dworzec Wileński", "Rondo Waszyngtona");
            case "13":
                return new DirectionsDTO("Dworzec Wileński", "Rondo Waszyngtona");
            case "15":
                return new DirectionsDTO("Dworzec Wileński", "Rondo Waszyngtona");
            case "17":
                return new DirectionsDTO("Dworzec Wileński", "Rondo Waszyngtona");
            case "102":
                return new DirectionsDTO("Dworzec Wileński", "Rondo Waszyngtona");
            case "103":
                return new DirectionsDTO("Dworzec Wileński", "Rondo Waszyngtona");
            case "104":
                return new DirectionsDTO("Dworzec Wileński", "Rondo Waszyngtona");
        }
        return null;
    }

    public static StopsDTO getStopsMocked(String line, String direction) {
        return new StopsDTO(List.of(
                new StopDTO("1", "Młociny", "52.254", "21.038"),
                new StopDTO("2", "Wawrzyszew", "52.254", "21.038"),
                new StopDTO("3", "Stare Bielany", "52.254", "21.038"),
                new StopDTO("4", "Słodowiec", "52.254", "21.038"),
                new StopDTO("5", "Marymont", "52.254", "21.038"),
                new StopDTO("6", "Plac Wilsona", "52.254", "21.038"),
                new StopDTO("7", "Dworzec Gdański", "52.254", "21.038"),
                new StopDTO("8", "Ratusz Arsenał", "52.254", "21.038"),
                new StopDTO("9", "Świętokrzyska", "52.254", "21.038")));
    }
}
