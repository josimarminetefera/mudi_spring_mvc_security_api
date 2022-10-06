package br.com.alura.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;

@Controller
@RequestMapping("pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private UserRepository userRepository;

	// http://localhost:8080/pedido/formulario
	@GetMapping("formulario")
	public String formulario(RequisicaoNovoPedido requisicao) {
		return "pedido/formulario";
	}

	// RequisicaoNovoPedido e uma classe so para armazenar antes de mandar para o bd
	// este @Valid serve para ativar as validacoes dentro da class
	// Se der algum erro na validacao ele vai vir por BindResult
	// BindingResult para verificar o resultado do @Valid
	@PostMapping("novo")
	public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result) {

		// Para nao fazer isso deve ser usado o @Valid
		// As validacoes devem estar dentro do DTO
		// if(requisicao.getNomeProduto == null || requisicao.getNomeProduto.isEmpty())

		if (result.hasErrors()) {
			return "pedido/formulario";
		}

		// getAuthentication()=São todos os dados do usuário logado
		// getContext() é da segurança do spring
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		User usuario = userRepository.findByUsername(username);
		Pedido pedido = requisicao.toPedido();
		pedidoRepository.save(pedido);
		pedido.setUser(usuario);
		// depois que salvar vai redirecionar para a pagina home
		return "redirect:/home";
	}

}
