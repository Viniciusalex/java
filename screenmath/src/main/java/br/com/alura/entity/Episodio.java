package br.com.alura.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Episodio {
    private String titulo;
    private Integer numero;
    private String avaliacoes;
    private LocalDate dataLancamento;


}
