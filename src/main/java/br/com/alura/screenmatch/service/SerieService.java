package br.com.alura.screenmatch.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.screenmatch.dto.SerieDto;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    // long id,
    // String titulo,
    // Integer totalTemporadas,
    // Double avaliacao,
    // LocalDate dataLancamento,
    // List<Categoria> generos,
    // List<Ator> atores,
    // String poster,
    // String sinopse

    public List<SerieDto> obterTodasAsSeries() {
        return converteParaListaSerieDto(serieRepository.findAll());
    }

    public List<SerieDto> obterTop5Series() {
        return converteParaListaSerieDto(serieRepository.findFirst5ByOrderByAvaliacaoDesc());
    }

    public List<SerieDto> obterSeriesRecentes() {
        var ano = 2015;
        var data = LocalDate.of(ano, 12, 31);

        return converteParaListaSerieDto(serieRepository.findByDataLancamentoAfter(data));
    }

    private List<SerieDto> converteParaListaSerieDto(List<Serie> listaSeries) {
        return listaSeries.stream()
                .map(s -> new SerieDto(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(),
                        s.getDataLancamento(), s.getGeneros(), s.getAtores(), s.getPoster(), s.getSinopse()))
                .toList();
    }

}
