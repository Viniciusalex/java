package com.example.screenmathvinicius.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ManyToAny;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


@Entity
@Table(name = "episodios")
public class Episodio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private Integer temporada;
	private String titulo;
	private Integer numeroEpisodio;
	private Double avaliacoes;
	private LocalDate dataLancamento;

    @ManyToOne
    @JoinColumn(name = "serie")
    private Serie serie;

    public Episodio() {}

	public Episodio(Integer numeroTemporada, DadosEpisodio dadosEpisodio) {
		this.temporada = numeroTemporada;
		this.titulo = dadosEpisodio.getTitulo();
		this.numeroEpisodio = dadosEpisodio.getNumeroEpisodio();

		try {
			this.avaliacoes = Double.valueOf(dadosEpisodio.getAvaliacoes());
		} catch (NumberFormatException ex) {
			this.avaliacoes = 0.0;
		}

		try {
			this.dataLancamento = LocalDate.parse(dadosEpisodio.getDataLancamento());
		} catch (DateTimeParseException ex) {
			this.dataLancamento = null;
		}
	}

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public Double getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(Double avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    @Override
	public String toString() {
		return "temporada=" + temporada + ", titulo='" + titulo + '\'' + ", numeroEpisodio=" + numeroEpisodio
				+ ", dataLancamento=" + dataLancamento;
	}

}