package br.com.serie.principal;

import br.com.serie.dto.DadosEpisodio;
import br.com.serie.dto.DadosSerieDto;
import br.com.serie.dto.DadosTemporadaDto;
import br.com.serie.dto.EpisodioDto;
import br.com.serie.entity.Episodio;
import br.com.serie.service.ConsumoApi;
import br.com.serie.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
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
        List<EpisodioDto> episodiosDto = temporadas.stream()
                .flatMap(t-> t.getEpisodios().stream()
                        .filter(d->!d.getAvaliacoes().equals("N/A"))
                        .sorted(Comparator.comparing(DadosEpisodio::getAvaliacoes, Comparator.reverseOrder()))
                        .limit(1)
                        .map(d-> new EpisodioDto(t.getNumero(),d)))
                .collect(Collectors.toList());

        episodiosDto.forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.getEpisodios().stream()
                        .map(d -> new Episodio(t.getNumero(), d)))
                .collect(Collectors.toList());

        System.out.println("\n");
        episodios.forEach(System.out::println);

//        System.out.println("\n Informe o nome: ");
//        var nomeEpisodio = sc.nextLine();
//
//        Optional<Episodio> episodioBuscado = episodios.stream()
//                .filter(e -> e.getTitulo().toUpperCase().contains(nomeEpisodio.toUpperCase()))
//                .findFirst();
//
//        if(episodioBuscado.isPresent()){
//            System.out.println("Episodio " + nomeEpisodio + " encontrado!");
//            System.out.println("Dados - " + " Temporada: " + episodioBuscado.get().getTemporada()+
//                    " Nome Episodio: " + episodioBuscado.get().getTitulo() +
//                    " Data de lançamento: " + episodioBuscado.get().getDataLancamento());
//        }
//

//        System.out.println("\n A partir de que ano você deseja ver os episodios");
//        var ano = sc.nextInt();
//        sc.nextLine();
//
//
//
//        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
//        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        episodios.stream().filter(e -> !e.getDataLancamento().equals(null) && e.getDataLancamento().isAfter(dataBusca))
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getTemporada() + " " +
//                                "Episodio: " + e.getTitulo() + " " +
//                                "Data lançamento: "  + e.getDataLancamento().format(formatador)
//                ));

        Map<Integer, Double> avaliacoesTemporada = episodios.stream().
                filter(e -> e.getAvaliacoes() > 0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getAvaliacoes)));

        System.out.println(avaliacoesTemporada);

        DoubleSummaryStatistics estat = episodios.stream().
                filter(e -> e.getAvaliacoes() > 0).
                collect(Collectors.summarizingDouble(Episodio::getAvaliacoes));

        System.out.println("\n");

        System.out.println("Media das avaliações: " + estat.getAverage()+
                            "\nMelhor epsodio: " + estat.getMax() +
                            "\nPior Episodio: " + estat.getMin() +
                            "\nNumero de episodios: " + estat.getCount());
    }
}
