package br.com.alura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosTemporadaDto {

    @JsonAlias("Season") private Integer numero;
    @JsonAlias("Episodes") private List<DadosEpisodio> episodios;
}
