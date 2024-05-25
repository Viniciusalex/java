package com.example.screenmathvinicius.repository;

import com.example.screenmathvinicius.model.Categoria;
import com.example.screenmathvinicius.model.Episodio;
import com.example.screenmathvinicius.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface serieRepository extends JpaRepository<Serie, Long> {

    //Vou procurar pelo titulo da serie
    Optional<Serie> findByTituloContainingIgnoreCase(String titulo);

    Optional<Serie> findByAtoresContainingIgnoreCase(String atores);

    List<Serie> findByAvaliacoesGreaterThan(String avaliacao);

//    //usar futuramente para mostrar top 5 series de acordo com avaliacao
//    List<Serie> findTop5ByorderByAvaliacoesDesc();

    List<Serie> findByGenero(Categoria categoria);

    List<Serie>  findByTotalTemporadasLessThanEqualAndAvaliacoesGreaterThanEqual(Integer totalTemporadas, String avaliacoes);

    //utilizando JPQL
    @Query("select s from Serie s WHERE s.totalTemporadas <= :totalTemporadas AND s.avaliacoes >= :avaliacoes")
    List<Serie> seriesPorTemporadaEAvaliacao(Integer totalTemporadas, String avaliacoes);

    //utilizando JPQL
    @Query("select e from Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:trechoEpisodio%")
    List<Episodio> SerieEpisodioPorTrecho(String trechoEpisodio);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.avaliacoes DESC LIMIT 5")
    List<Episodio> top5episodiosPorSerie(Serie serie);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie AND YEAR(e.dataLancamento) >= :anoLancamento")
    List<Episodio> episodioDepoisDeUmaData(Serie serie, int anoLancamento);
}
