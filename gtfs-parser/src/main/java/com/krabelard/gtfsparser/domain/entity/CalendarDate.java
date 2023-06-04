package com.krabelard.gtfsparser.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	@GeneratedValue
	private long id;

	@Column(name = "date")
	private Date date;

	@ManyToOne(fetch = FetchType.LAZY)
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
