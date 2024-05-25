package com.example.screenmathvinicius.principal;

import com.example.screenmathvinicius.model.*;
import com.example.screenmathvinicius.repository.serieRepository;
import com.example.screenmathvinicius.service.ConsumoApi;
import com.example.screenmathvinicius.service.ConverteDados;
import org.springframework.boot.autoconfigure.cache.CacheType;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

	Scanner sc = new Scanner(System.in);
	private ConsumoApi consumoApi = new ConsumoApi();
	private ConverteDados converteDados = new ConverteDados();
	private final String ENDERECO = "https://www.omdbapi.com/?t=";
	private final String KEY = "&apikey=550539de";
	private serieRepository repository;
	private List<DadosSerie> dadosSerie = new ArrayList<>();
	private List<Serie> series = new ArrayList<>();
	private Optional<Serie> serieBusca;

	public Principal(serieRepository repository) {
		this.repository = repository;
	}

	public void exibeMenu() {
		var menu = """
                1 - Buscar séries
                2 - Buscar episódios
                3 - Ver séries buscadas
                4 - Buscar serie por titulo
                5 - Buscar por Ator
                6 - Buscar por Avaliação
                7 - Buscar por Categoria
                8 - Buscar por Total de temporadas e avaliação desejada
                9 - Buscar episodio
                10 - Top 5 episodios por serie
                11 - Episodio por data
                0 - Sair
                """;
		var opcao = -1;
		while (opcao != 0) {
			System.out.println(menu);
			try {
				opcao = Integer.parseInt(sc.nextLine().trim());
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
					case 4:
						buscarSerieTitulo();
						break;
					case 5:
						buscarSerieAtor();
						break;
					case 6:
						buscarPorAvalicao();
						break;
					case 7:
						buscarCategoria();
						break;
					case 8:
						buscarTotalTemporadaEAvaliacao();
						break;
					case 9:
						buscarEpisodioPorTrecho();
						break;
					case 10:
						topEpisodiosPorSerie();
						break;
//					case 11:
//						buscarTop5series();
//						break;
					case 11:
						buscarEpisodioDepoisDeUmaData();
						break;
					case 0:
						System.out.println("Saindo...");
						break;
					default:
						System.out.println("Opção inválida");
				}
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida, por favor insira um número.");
			}
		}
	}

	private void buscarSerieWeb() {
		try {
			DadosSerie dados = getDadosSerie();
			Serie serie = new Serie(dados);
			repository.save(serie);
			System.out.println(dados);
		} catch (Exception e) {
			System.out.println("Serie não encontrada");
		}
	}

	private DadosSerie getDadosSerie() {
		System.out.println("Digite o nome da série para busca");
		var nomeSerie = sc.nextLine().trim();
		var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + KEY);
		DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);
		return dados;
	}

	private void buscarEpisodioPorSerie() {
		listarSeriesBuscadas();
		System.out.println("Escolha a serie pelo nome:");
		var nomeSerie = sc.nextLine().trim();


		Optional<Serie> serie = repository.findByTituloContainingIgnoreCase(nomeSerie);
		if (serie.isPresent()) {


			var serieEncontrada = serie.get();
			List<DadosTemporada> temporadas = new ArrayList<>();


			for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
				var json = consumoApi.obterDados(ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + KEY);
				DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
				temporadas.add(dadosTemporada);
			}
			temporadas.forEach(System.out::println);

			List<Episodio> episodios = temporadas.stream()
					.flatMap(d -> d.getEpisodios().stream().map(e -> new Episodio(d.getNumero(), e)))
					.collect(Collectors.toList());

			serieEncontrada.setEpisodios(episodios);
			repository.save(serieEncontrada);

		} else {
			System.out.println("Serie não encontrada");
		}
	}

	private void listarSeriesBuscadas() {
		series = repository.findAll();
		series.stream().sorted(Comparator.comparing(Serie::getGenero)).forEach(System.out::println);
	}


	private void buscarSerieTitulo(){
		System.out.println("Escolha o titulo da serie:");
		var nomeSerie = sc.nextLine().trim();

		serieBusca = repository.findByTituloContainingIgnoreCase(nomeSerie);

		if(serieBusca.isPresent()){
			System.out.println("****** Dados da serie ****** \n" + serieBusca.get());

		}else{
			System.out.println("Titulo não encontrado ");
		}

	}

	private void buscarSerieAtor(){
		System.out.println("Escolha o nome do ator:");
		var nomeAtor = sc.nextLine().trim();

		Optional<Serie> seriesEncontradas = repository.findByAtoresContainingIgnoreCase(nomeAtor);
		if (seriesEncontradas.isPresent()){
			System.out.println("***** Dados encontrados do ator " + nomeAtor + "*****");
			seriesEncontradas.stream().forEach(s -> System.out.println(s.getTitulo()+ " Avaliação: " + s.getAvaliacoes()));
		}else{
			System.out.println("Nenhum ator encontrado");
		}
	}

	private void buscarPorAvalicao(){
		System.out.println("Escolha a avalição que deseja procurar");
		String avaliacao = sc.nextLine().trim();

		List<Serie> serieAvaliacao = repository.findByAvaliacoesGreaterThan(avaliacao);

		if (!serieAvaliacao.isEmpty()){
			System.out.println("***** Dados da serie *****\n");
			serieAvaliacao.forEach(s-> System.out.println(s.getTitulo()));
		}else{
			System.out.println("Nenhuma serie encontrada");
		}
	}



//	public void buscarTop5series(){
//		List<Serie> buscarTop5series = repository.findTop5ByorderByAvaliacoesDesc();
//
//		buscarTop5series.forEach(s -> System.out.println(s.getTitulo()));
//	}

	private void buscarCategoria(){
		System.out.println("Escolha o genero que deseja procurar");
		String genero = sc.nextLine().trim();
		Categoria categoria = Categoria.fromPortugues(genero.toLowerCase().trim());
		List<Serie> serieGenero = repository.findByGenero(categoria);


		if (!serieGenero.isEmpty()){
			serieGenero.forEach(s -> System.out.println("Genero: " + s.getGenero() + " Titulo: " + s.getTitulo()));
		}else {
			System.out.println("Categoria não encotrada ");
		}
	}

	private void buscarTotalTemporadaEAvaliacao(){
		System.out.println("Escolha o total de temporadas que deseja que a serie tenha: ");
		Integer totalTemporadas = sc.nextInt();
		sc.nextLine();
		System.out.println("Escolha a avaliação que deseja: ");
		var avaliacao = sc.nextLine();

		List<Serie> seriesTemporadaAvaliacao = repository.seriesPorTemporadaEAvaliacao(totalTemporadas,avaliacao);

		if(!seriesTemporadaAvaliacao.isEmpty()){
			System.out.println("***** Dados da serie *****\n");
			seriesTemporadaAvaliacao.forEach(s-> System.out.println(s.getTitulo()));
		}else{
			System.out.println("Nenhuma serie encontrada");
		}

	}

	private void buscarEpisodioPorTrecho(){
		System.out.println("Escolha o nome do episodio: ");
		var trechoEpisodio = sc.nextLine().trim();


		List<Episodio> episodiosEcontrados = repository.SerieEpisodioPorTrecho(trechoEpisodio);

		if (!episodiosEcontrados.isEmpty()){
			System.out.println("***** Dados encontrados *****\n");
			episodiosEcontrados. forEach(e -> System.out.println("TITULO: " + e.getTitulo() + " - SERIE: " + e.getSerie().getTitulo()));
		}else{
			System.out.println("Nenhum episodio encontrado");
		}
	}

	private void topEpisodiosPorSerie(){
		buscarSerieTitulo();
		if(serieBusca.isPresent()){
			Serie serie = serieBusca.get();
			List<Episodio> topEpisodiosSerie = repository.top5episodiosPorSerie(serie);

			topEpisodiosSerie.forEach(e -> {
                System.out.println("TITULO: " + e.getTitulo() + " - SERIE: " + e.getSerie().getTitulo() + " - TEMPORADA: " + e.getTemporada());
            });
		}
	}

	private void buscarEpisodioDepoisDeUmaData(){
		buscarSerieTitulo();
		if(serieBusca.isPresent()){
			Serie serie = serieBusca.get();
			System.out.println("Escolha o ano desejada: : ");
			var dataLancamento = sc.nextInt();
			sc.nextLine();
			List<Episodio> episodioPorData = repository.episodioDepoisDeUmaData(serie, dataLancamento);
			episodioPorData.forEach(e-> System.out.println(e.getTitulo() + e.getDataLancamento()));
		}
	}
}