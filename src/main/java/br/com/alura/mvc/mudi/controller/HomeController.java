package br.com.alura.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

//http://localhost:8080/home
@Controller // para dizer que este m�todo vai controlar alguma tela
@RequestMapping("/home")
public class HomeController {

	// @Autowired cria uma instancia ao iniciar a classe
	@Autowired
	private PedidoRepository repository;

	// Model model e uma interface para mostrar e enviar coisas para o usuario
	// Principal principal voce consegue recuperar dados do usuario logado e regras
	@GetMapping() // http://localhost:8080/home
	public String home(Model model, Principal principal) {
		System.out.println("----------------- HomeController /home");

		// paginação e ordenação
		Sort sort = Sort.by("dataDaEntrega").descending();
		PageRequest paginacao = PageRequest.of(0, 2, sort);

		// Não é para buscar toda a lista de pedidos tem que ser por usuário
		// List<Pedido> pedidos = repository.findAll();
		// List<Pedido> pedidos = repository.findAllByUsuario(principal.getName());
		List<Pedido> pedidos = repository.findByStatus(StatusPedido.ENTREGUE, paginacao);
		
		model.addAttribute("pedidos", pedidos); // lista de pedidos que vai para a pagina html
		return "home";
	}

	// http://localhost:8080/home/aguardando
	// http://localhost:8080/home/aprovado
	// http://localhost:8080/home/entregue
	// pegar variavel que vem no path @PathVariable("status") e joga para dentro do
	// String status
	/*
	 * @GetMapping("/{status}") public String porStatus(@PathVariable("status")
	 * String status, Model model) {
	 * System.out.println("----------------- HomeController /status"); List<Pedido>
	 * pedidos =
	 * repository.findByStatus(StatusPedido.valueOf(status.toUpperCase()));
	 * model.addAttribute("pedidos", pedidos); model.addAttribute("status", status);
	 * return "home"; }
	 */

	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}
}
