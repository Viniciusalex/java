package com.example.screenmathvinicius.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosEpisodio {
	@JsonAlias("Title")
	private String titulo;
	
	@JsonAlias("Episode")
	private Integer numeroEpisodio;
	
	@JsonAlias("imdbRating")
	private String avaliacoes;
	
	@JsonAlias("Released")
	private String dataLancamento;

	public DadosEpisodio() {
	}

	public DadosEpisodio(String titulo, Integer numeroEpisodio, String avaliacoes, String dataLancamento) {
		this.titulo = titulo;
		this.numeroEpisodio = numeroEpisodio;
		this.avaliacoes = avaliacoes;
		this.dataLancamento = dataLancamento;
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

	public String getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(String avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public String getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
}