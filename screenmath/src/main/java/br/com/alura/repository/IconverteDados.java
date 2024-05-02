package br.com.alura.repository;

public interface IconverteDados {

    <T> T obterDados(String json, Class<T> classe);
}
