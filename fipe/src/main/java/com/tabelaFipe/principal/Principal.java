package com.tabelaFipe.principal;

import com.tabelaFipe.model.Dados;
import com.tabelaFipe.model.Veiculos;
import com.tabelaFipe.service.ConsumoApi;
import com.tabelaFipe.service.ConverterDados;
import br.com.alura.tabelafipe.model.Modelos;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    Scanner sc = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverterDados converterDados = new ConverterDados();
    private final String URL_BASE ="https://parallelum.com.br/fipe/api/v1/";


    public void exibirMenu(){
        System.out.println("Informe o tipo do automovel:\n" +
                "1 - CARRO\n" + "2 - MOTO\n" + "3 - CAMINHÃO\n");
        int escolha = sc.nextInt();
        String endereco = "";
        switch (escolha){
            case 1:
                endereco = URL_BASE + "carros/marcas";
                break;
            case 2:
                endereco = URL_BASE + "motos/marcas";
                break;
            case 3:
                endereco = URL_BASE + "caminhoes/marcas";
                break;
            default:
                System.out.println("Opção invalida");
        }

        var json = consumoApi.obterDados(endereco);
//        System.out.println(json);
        var marcas = converterDados.obterLista(json, Dados.class);

        marcas.stream().sorted(Comparator.comparing(Dados::getCodigo)).forEach(System.out::println);

        sc.nextLine();

        System.out.println("Informe o codigo para consultar: ");
        var codigoMarca = sc.nextLine();

        endereco  = endereco + "/" + codigoMarca + "/modelos";
        json = consumoApi.obterDados(endereco);
        var modeloLista = converterDados.obterDados(json, Modelos.class);

        System.out.println("\n Modelos dessa marca: ");
        modeloLista.modelos().stream().sorted(Comparator.comparing(Dados::getCodigo)).forEach(System.out::println);w

//        System.out.println("\n Digite um trecho do nome do carro a ser buscado: ");
//        var nomeCarro = sc.nextLine();
//
//        List<Dados> modelosFiltrados = modelosLista.getModelos().stream().filter(m -> m.getNome().toLowerCase()
//                .contains(nomeCarro.toLowerCase())).collect(Collectors.toList());
//
//        System.out.println("\n Modelos de filtrados do carro: ");
//        modelosFiltrados.forEach(System.out::println);
//
//        System.out.println("\n Digite por favor o código do modelo para buscar os valores de avaliação: ");
//        var codigoModelo = sc.nextLine();
//
//        endereco  = endereco + "/" + codigoModelo + "/anos";
//
//        json = consumoApi.obterDados(endereco);
//        List<Dados> anos = converterDados.obterLista(json, Dados.class);
//        List<Veiculos> veiculos = new ArrayList<>();
//
//        for (int i = 0; i < anos.size() ; i++) {
//            var enderecosAnos = endereco + "/" + anos.get(i).getCodigo();
//            json = consumoApi.obterDados(enderecosAnos);
//            Veiculos veiculo = converterDados.obterDados(json, Veiculos.class);
//            veiculos.add(veiculo);
//        }
//
//        System.out.println("\nTodos os veículos filtrados com avaliações por ano: ");
//        veiculos.forEach(System.out::println);
      }

}
