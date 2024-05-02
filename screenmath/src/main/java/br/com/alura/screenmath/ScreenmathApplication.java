package br.com.alura.screenmath;

import br.com.alura.dto.DadosEpisodio;
import br.com.alura.dto.DadosSerieDto;
import br.com.alura.dto.DadosTemporadaDto;
import br.com.alura.principal.Principal;
import br.com.alura.service.ConsumoApi;
import br.com.alura.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmathApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmathApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal();
		principal.exibeMenu();

	}
}
