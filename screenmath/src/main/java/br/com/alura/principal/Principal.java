package br.com.alura.principal;

import br.com.alura.dto.DadosEpisodio;
import br.com.alura.dto.DadosSerieDto;
import br.com.alura.dto.DadosTemporadaDto;
import br.com.alura.dto.EpisodioDto;
import br.com.alura.service.ConsumoApi;
import br.com.alura.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    Scanner sc = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String KEY = "&apikey=550539de";

    public void exibeMenu(){
        System.out.println("Digite o nome da Serie que você deseja: ");
        var nomeSerie = sc.nextLine();
        var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ","+") +KEY);
        DadosSerieDto dadosSerieDto = converteDados.obterDados(json, DadosSerieDto.class);
        System.out.println("\n ****Dados da Serie****");
        System.out.println(dadosSerieDto);

        List<DadosTemporadaDto> temporadas= new ArrayList<>();


		for (int i = 1; i <= Integer.parseInt(String.valueOf(dadosSerieDto.getTotalTemporadas())); i++) {
            json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ","+")+"&season="+i+KEY);
			DadosTemporadaDto dadosTemporadaDto = converteDados.obterDados(json, DadosTemporadaDto.class);
			temporadas.add(dadosTemporadaDto);
		}

        System.out.println("\n *****Todos temporas da Série*****");
		temporadas.forEach(System.out::println);
        System.out.println("\n *****Todos episodios da Série*****");
        temporadas.forEach(t -> t.getEpisodios().forEach(e-> System.out.println(e.getTitulo())));



        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t-> t.getEpisodios().stream())
                .collect(Collectors.toList());


        System.out.println("\n Top 5 episodios");
        dadosEpisodios.stream()
                .filter(e-> !e.getAvaliacoes().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::getAvaliacoes).reversed())
                .limit(5)
                .forEach(System.out::println);

        System.out.println("\n");


        System.out.println("\n Top episodios de cada temporada!");
        List<EpisodioDto> episodios = temporadas.stream()
                .flatMap(t-> t.getEpisodios().stream()
                        .filter(d->!d.getAvaliacoes().equals("N/A"))
                        .sorted(Comparator.comparing(DadosEpisodio::getAvaliacoes, Comparator.reverseOrder()))
                        .limit(1)
                        .map(d-> new EpisodioDto(t.getNumero(),d)))
                .collect(Collectors.toList());

        episodios.forEach(System.out::println);

    }
}
