package br.com.serie.entity;

import br.com.serie.dto.DadosEpisodio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Episodio {
    private Integer temporada;
    private String titulo;
    private Integer numero;
    private Double avaliacoes;
    private LocalDate dataLancamento;


    public Episodio(Integer numeroTemporada, DadosEpisodio dadosEpisodio) {
        this.temporada = numeroTemporada;
        this.titulo = dadosEpisodio.getTitulo();
        this.numero = dadosEpisodio.getNumeroEpisodio();
        try {
            this.avaliacoes = Double.valueOf(dadosEpisodio.getAvaliacoes());
        }catch (NumberFormatException ex){
            this.avaliacoes = 0.0;
        }

        try {
            this.dataLancamento = LocalDate.parse(dadosEpisodio.getDataLancamento());
        }catch (DateTimeParseException ex){
            this.dataLancamento = null;
        }
    }

}
