package com.example.screenmathvinicius.model;

public enum Categoria {

	ACAO("Action", "acao"),
	ROMANCE("Romance", "romance"),
	COMEDIA("Comedy", "comedia"),
	DRAMA("Drama", "drama"),
	CRIME("Crime", "crime");

	public String categoriaOmdb;

	private String categoriaPortugues;

	private Categoria(String categoriaOmdb, String categoriaPortugues) {

		this.categoriaOmdb = categoriaOmdb;
		this.categoriaPortugues = categoriaPortugues;
	}

	public static Categoria fromString(String text) {
		for (Categoria categoria : Categoria.values()) {
			if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
				return categoria;
			}
		}
		throw new IllegalArgumentException("Nenhuma categoria encontrada: " + text);
	}

	public static Categoria fromPortugues(String text) {
		for (Categoria categoria : Categoria.values()) {
			if (categoria.categoriaPortugues.equalsIgnoreCase(text)) {
				return categoria;
			}
		}
		throw new IllegalArgumentException("Nenhuma categoria encontrada: " + text);
	}
}