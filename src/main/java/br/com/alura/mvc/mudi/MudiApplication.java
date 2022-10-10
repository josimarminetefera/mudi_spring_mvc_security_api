package br.com.alura.mvc.mudi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching // Isso habilita o uso de cash na aplicação
@SpringBootApplication
public class MudiApplication {

	public static void main(String[] args) {
		// Esta é a primeira class chamada.
		System.out.println("----------------- MudiApplication");
		SpringApplication.run(MudiApplication.class, args);
	}

}
