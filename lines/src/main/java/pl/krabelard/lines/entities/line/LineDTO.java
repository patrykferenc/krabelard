package pl.krabelard.lines.entities.line;

import lombok.Data;

@Data
public class LineDTO {

	String vehicleType;

	String line;

	public LineDTO(String lineNumber) {
		this.vehicleType = identifyType(lineNumber).toString();
		this.line = lineNumber;
	}

	public static VehicleType identifyType(String lineNumber) {
		if (lineNumber.contains("M")) {
			return VehicleType.METRO;
		} else if (lineNumber.contains("S")) {
			return VehicleType.TRAIN;
		} else if (lineNumber.length() < 3) {
			return VehicleType.TRAM;
		} else if (lineNumber.length() >= 3) {
			return VehicleType.BUS;
		} else {
			throw new IllegalArgumentException("Unknown line type: " + lineNumber);
		}
	}
}
