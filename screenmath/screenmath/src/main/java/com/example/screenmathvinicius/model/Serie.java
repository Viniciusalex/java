package com.example.screenmathvinicius.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "series")
public class Serie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo;

	private Integer totalTemporadas;

	private String avaliacoes;


	@Enumerated(EnumType.STRING)
	// @Enumerated(EnumType.STRING) - vai gravar o nome da string que foi
	// selecionada, tem outros tipos que gravaria o numero de acordo com a sequencia
	private Categoria genero;

	private String atores;

	private String poster;

	private String sinopse;

	@OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Episodio> episodios = new ArrayList<>();

	public Serie() {
	}

	public Serie(DadosSerie dadosSerie) {
		this.titulo = dadosSerie.getTitulo();
		this.totalTemporadas = dadosSerie.getTotalTemporadas();
		this.avaliacoes = dadosSerie.getAvaliacoes() != null
				? String.valueOf(Double.parseDouble(dadosSerie.getAvaliacoes()))
				: "0.0";
		this.genero = Categoria.fromString(dadosSerie.getGenero().split(",")[0].trim());
		this.atores = dadosSerie.getAtores();
		this.poster = dadosSerie.getPoster();
		this.sinopse = dadosSerie.getSinopse();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Categoria getGenero() {
		return genero;
	}

	public void setGenero(Categoria genero) {
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

	public List<Episodio> getEpisodios() {
		return episodios;
	}

	public void setEpisodios(List<Episodio> episodios) {
		episodios.forEach(e->e.setSerie(this));
		this.episodios = episodios;
	}

	@Override
	public String toString() {
		return "Serie{" +
				"genero=" + genero +
				", episodios=" + episodios +
				", avaliacoes='" + avaliacoes + '\'' +
				", totalTemporadas=" + totalTemporadas +
				", titulo='" + titulo + '\'' +
				", id=" + id +
				'}';
	}
}