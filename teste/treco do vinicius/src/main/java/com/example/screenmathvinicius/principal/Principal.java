package com.example.screenmathvinicius.principal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.example.screenmathvinicius.model.DadosSerie;
import com.example.screenmathvinicius.model.DadosTemporada;
import com.example.screenmathvinicius.model.Serie;
import com.example.screenmathvinicius.service.ConsumoApi;
import com.example.screenmathvinicius.service.ConverteDados;

public class Principal {

	Scanner sc = new Scanner(System.in);
	private ConsumoApi consumoApi = new ConsumoApi();
	private ConverteDados converteDados = new ConverteDados();
	private final String ENDERECO = "https://www.omdbapi.com/?t=";
	private final String KEY = "&apikey=550539de";

	List<DadosSerie> dadosSerie = new ArrayList<>();

	public void exibeMenu() {
		var menu = """
				1 - Buscar séries
				2 - Buscar episódios
				3 - Ver séries buscadas
				0 - Sair
				""";
		var opcao = -1;
		while (opcao != 0) {
			System.out.println(menu);
			opcao = sc.nextInt();
			switch (opcao) {
			case 1:
				buscarSerieWeb();
				break;
			case 2:
				buscarEpisodioPorSerie();
				break;
			case 3:
				listarSeriesBuscadas();
				break;
			case 0:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida");
			}
		}
	}

	private void buscarSerieWeb() {
		DadosSerie dados = getDadosSerie();
		dadosSerie.add(dados);
		System.out.println(dados);
	}

	private DadosSerie getDadosSerie() {
		System.out.println("Digite o nome da série para busca");
		sc.nextLine();
		var nomeSerie = sc.nextLine();
		var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + KEY);
		DadosSerie dadosSerie = converteDados.obterDados(json, DadosSerie.class);
		return dadosSerie;
	}

	private void buscarEpisodioPorSerie() {
		DadosSerie dadosSerie = getDadosSerie();
		List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i <= dadosSerie.getTotalTemporadas(); i++) {
			var json = consumoApi
					.obterDados(ENDERECO + dadosSerie.getTitulo().replace(" ", "+") + "&season=" + i + KEY);
			DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println);
	}

	private void listarSeriesBuscadas() {
		List<Serie> series = new ArrayList<>();
		series = dadosSerie.stream().map(d -> new Serie(d)).collect(Collectors.toList());
		series.stream().sorted(Comparator.comparing(Serie::getGenero)).forEach(System.out::println);
	}
}