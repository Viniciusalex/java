package com.example.screenmathvinicius.repository;

public interface IconverteDados {

	<T> T obterDados(String json, Class<T> classe);

}