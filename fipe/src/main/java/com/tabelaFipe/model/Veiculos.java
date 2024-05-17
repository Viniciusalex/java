package com.tabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Veiculos {
        @JsonAlias("valor") private String valor;
        @JsonAlias("marca") private String marca;
        @JsonAlias("modelo") private String modelo;
        @JsonAlias("AnoModelo") private Integer ano;
        @JsonAlias("Combustivel") private String tipoCombustivel;
}
