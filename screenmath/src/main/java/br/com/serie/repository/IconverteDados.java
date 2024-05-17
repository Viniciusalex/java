package br.com.serie.repository;

public interface IconverteDados {

    <T> T obterDados(String json, Class<T> classe);
}
