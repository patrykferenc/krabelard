package com.krabelard.gtfsparser.domain.usecase;

import com.krabelard.gtfsparser.domain.entity.Trip;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, String> {
	Set<Trip> findTripByServiceId(String serviceId);
}
