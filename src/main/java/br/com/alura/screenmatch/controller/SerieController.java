package br.com.alura.screenmatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.screenmatch.dto.SerieDto;
import br.com.alura.screenmatch.service.SerieService;

@RestController
public class SerieController {

    @Autowired
    SerieService serieService;

    @GetMapping("/series")
    public List<SerieDto> obterSeries() {
        return serieService.obterTodasAsSeries();
    }

    @GetMapping("/series/top5")
    public List<SerieDto> obterTop5Series() {
        return serieService.obterTop5Series();
    }

    @GetMapping("/series/lancamentos")
    public List<SerieDto> obterLancamentos() {
        return serieService.obterSeriesRecentes();
    }
}
