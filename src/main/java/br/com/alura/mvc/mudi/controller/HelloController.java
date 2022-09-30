package br.com.alura.mvc.mudi.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.alura.mvc.mudi.model.Pedido;

@Controller
public class HelloController {

	// Testando comunicação com o banco de dados
	@PersistenceContext
	private EntityManager entityManager;

	// http://localhost:8080/hello
	@GetMapping("/hello")
	public String hello(Model model) {
		System.out.println("----------------- HelloController /hello");
		model.addAttribute("nome", "Mundo");
		// adicionando atributos
		model.addAttribute("nome", "Peste do Mal");
		model.addAttribute("teste", "Teste");

		// Testando consulta aos pedidos usando EntityManager
		Query query = entityManager.createQuery("select p from Pedido p", Pedido.class);
		List<Pedido> pedidos = query.getResultList();
		pedidos.forEach(p -> System.out.println(p.getNomeProduto()));
		for (Pedido item : pedidos) {
			System.out.print(item.getNomeProduto());
		}

		return "hello";
	}
}
