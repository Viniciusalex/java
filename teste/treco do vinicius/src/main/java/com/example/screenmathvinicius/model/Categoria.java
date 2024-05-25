package com.example.screenmathvinicius.model;

public enum Categoria {

	ACAO("Action"), ROMANCE("Romance"), COMEDIA("Comedy"), DRAMA("Drama"), CRIME("Crime");

	public String categoriaOmdb;

	private Categoria(String categoriaOmdb) {
		this.categoriaOmdb = categoriaOmdb;
	}

	public static Categoria fromString(String text) {
		for (Categoria categoria : Categoria.values()) {
			if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
				return categoria;
			}
		}
		throw new IllegalArgumentException("Nenhuma categoria encontrada: " + text);
	}
}