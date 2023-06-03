package pl.krabelard.lines.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.krabelard.lines.entities.LinesDTO;
import pl.krabelard.lines.services.LineService;

@RestController
@RequestMapping("/lines")
@RequiredArgsConstructor
public class LinesController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public LinesDTO getAllLines(){
        return LineService.getAllLinesMocked();
    }

}
