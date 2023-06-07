package com.krabelard.gtfsparser.domain.usecase;

import com.krabelard.gtfsparser.domain.entity.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopRepository extends JpaRepository<Stop, String> {}
