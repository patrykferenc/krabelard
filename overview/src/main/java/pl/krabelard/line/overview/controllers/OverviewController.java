package pl.krabelard.line.overview.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.krabelard.line.overview.entities.Stop;
import pl.krabelard.line.overview.services.OverviewService;

import java.util.Collection;

@Controller
@RequestMapping("/overview")
@RequiredArgsConstructor
public class OverviewController {

    private final OverviewService overviewService;

    @GetMapping("/{line}")
    public Collection<Stop> getStopsForLine(
            @PathVariable("line") int line
    ){
        return overviewService.getStopsForLine(line);
    }
}
