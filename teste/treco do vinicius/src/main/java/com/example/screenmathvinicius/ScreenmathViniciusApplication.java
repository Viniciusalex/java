package com.example.screenmathvinicius;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.screenmathvinicius.principal.Principal;

@SpringBootApplication
public class ScreenmathViniciusApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmathViniciusApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal();
		principal.exibeMenu();

	}

}
