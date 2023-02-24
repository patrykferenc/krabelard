package pl.krabelard.timetables.line.domain.entity;

import java.time.LocalTime;
import java.util.List;

public record LineTimetable(List<LocalTime> arrivals) {}
