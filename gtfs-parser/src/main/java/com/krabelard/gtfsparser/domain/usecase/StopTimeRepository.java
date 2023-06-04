package com.krabelard.gtfsparser.domain.usecase;

import com.krabelard.gtfsparser.domain.entity.StopTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopTimeRepository extends JpaRepository<StopTime, Long> {}
