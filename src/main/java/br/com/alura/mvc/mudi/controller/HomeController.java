package br.com.alura.mvc.mudi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

//http://localhost:8080/home
@Controller // para dizer que este método vai controlar alguma tela
@RequestMapping("/home")
public class HomeController {

	// @Autowired cria uma instancia ao iniciar a classe
	@Autowired
	private PedidoRepository repository;

	// Model model é uma interface para mostrar coisas para o usuário
	// Principal principal voce consegue recuperar dados do usuário logado e regras
	@GetMapping() // http://localhost:8080/home
	public String home(Model model) {
		System.out.println("----------------- HomeController /home");
		List<Pedido> pedidos = repository.findAll();
		model.addAttribute("pedidos", pedidos); // lista de pedidos que vai para a pagina html
		return "home";
	}

	// http://localhost:8080/home/aguardando
	// http://localhost:8080/home/aprovado
	// http://localhost:8080/home/entregue
	// pegar variavel que vem no path @PathVariable("status") e joga para dentro do
	// String status
	@GetMapping("/{status}")
	public String porStatus(@PathVariable("status") String status, Model model) {
		System.out.println("----------------- HomeController /status");
		List<Pedido> pedidos = repository.findByStatus(StatusPedido.valueOf(status.toUpperCase()));
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status", status);
		return "home";
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}
}
