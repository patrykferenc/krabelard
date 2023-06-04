package com.krabelard.gtfsparser.domain.usecase;

import com.krabelard.gtfsparser.domain.entity.Frequency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrequencyRepository extends JpaRepository<Frequency, Long> {}
