package br.com.alura.screenmatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.screenmatch.dto.SerieDto;
import br.com.alura.screenmatch.repository.SerieRepository;

@RestController
public class SerieController {
    @Autowired
    private SerieRepository serieRepository;

    @GetMapping("/series")
    public List<SerieDto> obterSeries() {
        return serieRepository.findAll().stream()
                .map(s -> new SerieDto(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(),
                        s.getGeneros(), s.getAtores(), s.getPoster(), s.getSinopse()))
                .toList();

        // long id,
        // String titulo,
        // Integer totalTemporadas,
        // Double avaliacao,
        // List<Categoria> generos,
        // List<Ator> atores,
        // String poster,
        // String sinopse
    }
}
