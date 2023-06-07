package com.krabelard.gtfsparser.domain.usecase;

import com.krabelard.gtfsparser.domain.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, String> {}
