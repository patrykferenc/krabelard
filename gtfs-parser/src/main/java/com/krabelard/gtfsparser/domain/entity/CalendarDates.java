package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "calendar_dates")
@Getter
@Setter
public class CalendarDates {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "date")
	private Date date;

	@ManyToOne
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
	}
}
