package com.example.screenmathvinicius.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {
	private Integer temporada;
	private String titulo;
	private Integer numeroEpisodio;
	private Double avaliacoes;
	private LocalDate dataLancamento;

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

	public Double getAvaliacao() {
		return avaliacoes;
	}

	public void setAvaliacao(Double avaliacao) {
		this.avaliacoes = avaliacao;
	}

	@Override
	public String toString() {
		return "temporada=" + temporada + ", titulo='" + titulo + '\'' + ", numeroEpisodio=" + numeroEpisodio
				+ ", dataLancamento=" + dataLancamento;
	}

}