package pl.krabelard.lines.entities;

import lombok.Data;

@Data
public class LineDTO {

    String lineNumber;
    String lineType;

    public LineDTO(String lineNumber) {
        this.lineType = identifyType(lineNumber).toString();
        this.lineNumber = lineNumber;
    }

    public static VehicleType identifyType(String lineNumber) {

        if(lineNumber.contains("M")) {
            return VehicleType.METRO;
        }
        else if(lineNumber.contains("S")) {
            return VehicleType.TRAIN;
        }
        else if(lineNumber.length() < 3) {
            return VehicleType.TRAM;
        }
        else if(lineNumber.length() >= 3) {
            return VehicleType.BUS;
        }
        else {
            throw new IllegalArgumentException("Unknown line type: " + lineNumber);
        }
    }
}
