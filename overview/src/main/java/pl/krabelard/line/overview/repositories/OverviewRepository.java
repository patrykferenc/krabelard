package pl.krabelard.line.overview.repositories;

import pl.krabelard.line.overview.entities.Stop;

import java.util.Collection;

public interface OverviewRepository {
    Collection<Stop> getStopsForLine(int line);
}
