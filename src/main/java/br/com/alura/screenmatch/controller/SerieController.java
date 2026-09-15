package br.com.alura.screenmatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.screenmatch.dto.EpisodioDto;
import br.com.alura.screenmatch.dto.SerieDto;
import br.com.alura.screenmatch.service.SerieService;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    SerieService serieService;

    @GetMapping
    public List<SerieDto> obterSeries() {
        return serieService.obterTodasAsSeries();
    }

    @GetMapping("/top5")
    public List<SerieDto> obterTop5Series() {
        return serieService.obterTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<SerieDto> obterLancamentos() {
        // return serieService.obterSeriesRecentes();
        return serieService.obterSeriesComEpisodiosRecentes();
    }

    @GetMapping("/{id}")
    public SerieDto obterPorId(@PathVariable Long id) {
        return serieService.obterSeriePorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDto> obterTodasAsTemporadas(@PathVariable Long id) {
        return serieService.obterEpisodiosTodasTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{numeroTemporada}")
    public List<EpisodioDto> obterEpisodiosPorTemporada(@PathVariable Long id, @PathVariable Integer numeroTemporada) {
        return serieService.obterEpisodiosUmaTemporada(id, numeroTemporada);
    }

}
