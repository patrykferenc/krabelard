package pl.krabelard.line.overview.repositories.implementation;

import org.springframework.stereotype.Component;
import pl.krabelard.line.overview.entities.Stop;
import pl.krabelard.line.overview.repositories.OverviewRepository;

import java.util.Collection;

@Component
public class OverviewRepositoryImplementation implements OverviewRepository {

    @Override
    public Collection<Stop> getStopsForLine(int line){
        return null;
    }
}
