package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import lombok.*;

@Entity
@Table(name = "calendar_date")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarDate {

	@Id
	@Column(name = "service_id")
	private String serviceId;

	@Column(name = "date")
	private LocalDate date;

	@OneToMany(mappedBy = "serviceId")
	private Set<Trip> trips;

	@Column(name = "exception_type")
	@Enumerated(EnumType.ORDINAL)
	private ExceptionType exceptionType;

	public enum ExceptionType {
		AddedForSpecifiedDate(1),
		RemovedForSpecifiedDate(2);

		private final int typeId;

		ExceptionType(int typeId) {
			this.typeId = typeId;
		}

		public int numericalValue() {
			return typeId;
		}

		public static ExceptionType of(int id) {
			return ExceptionType.values()[id];
		}
	}
}
