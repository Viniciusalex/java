package br.com.serie.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosEpisodio {
    @JsonAlias("Title") private String titulo;
    @JsonAlias("Episode") private Integer numeroEpisodio;
    @JsonAlias("imdbRating") private String avaliacoes;
    @JsonAlias("Released") private String dataLancamento;
}
