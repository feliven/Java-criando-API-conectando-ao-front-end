package br.com.alura.screenmatch.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.screenmatch.dto.EpisodioDto;
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

    public List<SerieDto> obterSeriesComEpisodiosRecentes() {
        return converteParaListaSerieDto(serieRepository.filtrarSeriesPorEpisodiosRecentes());
    }

    public SerieDto obterSeriePorId(Long id) {
        var serie = serieRepository.findById(id);

        if (serie.isPresent()) {
            return converteParaSerieDto(serie.get());
        }

        return null;
    }

    public List<EpisodioDto> obterEpisodiosTodasTemporadas(Long id) {
        var serie = serieRepository.findById(id);

        if (serie.isPresent()) {
            return serie.get().getEpisodios().stream().map(
                    e -> new EpisodioDto(
                            e.getId(), e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio(),
                            e.getAvaliacao(), e.getDataLancamento()))
                    .toList();
        }

        return null;
    }

    public List<EpisodioDto> obterEpisodiosUmaTemporada(Long id, Integer numeroTemporada) {
        var serie = serieRepository.findById(id);

        if (serie.isPresent()) {
            return serie.get().getEpisodios().stream()
                    .filter(s -> s.getTemporada() == numeroTemporada)
                    .map(
                            e -> new EpisodioDto(
                                    e.getId(), e.getTemporada(), e.getTitulo(), e.getNumeroEpisodio(),
                                    e.getAvaliacao(), e.getDataLancamento()))
                    .toList();
        }

        return null;
    }

    private SerieDto converteParaSerieDto(Serie serie) {
        return new SerieDto(serie.getId(), serie.getTitulo(), serie.getTotalTemporadas(), serie.getAvaliacao(),
                serie.getDataLancamento(), serie.getGeneros(), serie.getAtores(), serie.getPoster(),
                serie.getSinopse());
    }

    private List<SerieDto> converteParaListaSerieDto(List<Serie> listaSeries) {
        return listaSeries.stream()
                .map(s -> converteParaSerieDto(s))
                .toList();
    }

}
