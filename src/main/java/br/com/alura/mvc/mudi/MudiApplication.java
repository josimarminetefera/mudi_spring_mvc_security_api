package br.com.alura.mvc.mudi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MudiApplication {

	public static void main(String[] args) {
		// Esta é a primeira class chamada.
		System.out.println("----------------- MudiApplication");
		SpringApplication.run(MudiApplication.class, args);
	}

}
