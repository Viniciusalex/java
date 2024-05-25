package com.example.screenmathvinicius.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DadosSerie {
	@JsonAlias("Title")
	private String titulo;

	@JsonAlias("totalSeasons")
	private Integer totalTemporadas;

	@JsonAlias("imdbRating")
	private String avaliacoes;

	@JsonAlias("Genre")
	private String genero;

	@JsonAlias("Actors")
	private String atores;

	@JsonAlias("Poster")
	private String poster;

	@JsonAlias("Plot")
	private String sinopse;

	public DadosSerie() {
	}

	public DadosSerie(String titulo, Integer totalTemporadas, String avaliacoes, String genero, String atores,
			String poster, String sinopse) {
		this.titulo = titulo;
		this.totalTemporadas = totalTemporadas;
		this.avaliacoes = avaliacoes;
		this.genero = genero;
		this.atores = atores;
		this.poster = poster;
		this.sinopse = sinopse;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getTotalTemporadas() {
		return totalTemporadas;
	}

	public void setTotalTemporadas(Integer totalTemporadas) {
		this.totalTemporadas = totalTemporadas;
	}

	public String getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(String avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getAtores() {
		return atores;
	}

	public void setAtores(String atores) {
		this.atores = atores;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
}