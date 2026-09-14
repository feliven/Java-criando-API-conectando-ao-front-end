package br.com.alura.screenmatch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.screenmatch.dto.SerieDto;
import br.com.alura.screenmatch.repository.SerieRepository;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    public List<SerieDto> obterTodasAsSeries() {

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
