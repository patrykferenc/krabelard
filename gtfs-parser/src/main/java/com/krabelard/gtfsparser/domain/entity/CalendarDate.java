package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "calendar_date")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarDate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "date")
	private Date date;

	@ManyToOne(
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL
	)
	@JoinColumn(name = "service_id")
	private Trip serviceId;

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
