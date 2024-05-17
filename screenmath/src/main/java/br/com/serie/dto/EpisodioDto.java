package br.com.serie.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Generated
@Data
public class EpisodioDto {
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double avaliacoes;
    private LocalDate dataLancamento;


    public EpisodioDto(Integer numeroTemporada, DadosEpisodio dadosEpisodio) {
        this.temporada = numeroTemporada;
        this.titulo = dadosEpisodio.getTitulo();
        this.avaliacoes = Double.valueOf(dadosEpisodio.getAvaliacoes());
        this.numeroEpisodio = dadosEpisodio.getNumeroEpisodio();
        this.dataLancamento = LocalDate.parse(dadosEpisodio.getDataLancamento());
    }


}
