package pl.krabelard.line.overview.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.krabelard.line.overview.entities.Stop;
import pl.krabelard.line.overview.repositories.OverviewRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class OverviewService {
    private final OverviewRepository overviewRepository;

    public Collection<Stop> getStopsForLine(int line){
        return overviewRepository.getStopsForLine(line);
    }
}
