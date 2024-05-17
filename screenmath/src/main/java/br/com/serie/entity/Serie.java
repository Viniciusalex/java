package br.com.serie.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Serie {
    private String titulo, avaliacao;
    private Integer totalTemporadas;
}
