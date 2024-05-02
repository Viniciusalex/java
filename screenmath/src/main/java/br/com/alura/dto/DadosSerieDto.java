package br.com.alura.dto;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.NonNull;


@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosSerieDto {
        @JsonAlias("Title") private String titulo;
        @JsonAlias("totalSeasons") private Integer totalTemporadas;
        @JsonAlias("imdbRating") private String avaliacoes;

}
