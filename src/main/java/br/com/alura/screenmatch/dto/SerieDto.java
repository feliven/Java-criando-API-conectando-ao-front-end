package br.com.alura.screenmatch.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.alura.screenmatch.model.Ator;
import br.com.alura.screenmatch.model.Categoria;

public record SerieDto(
        long id,
        String titulo,
        Integer totalTemporadas,
        Double avaliacao,
        LocalDate dataLancamento,
        List<Categoria> generos,
        List<Ator> atores,
        String poster,
        String sinopse) {
}
