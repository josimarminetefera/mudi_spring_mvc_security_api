package br.com.alura.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;

	// http://localhost:8080/pedido/formulario
	@GetMapping("formulario")
	public String formulario(RequisicaoNovoPedido requisicao) {
		return "pedido/formulario";
	}

	// RequisicaoNovoPedido é uma classe só para armazenar antes de mandar para o bd
	// este @Valid serve para ativar as validações dentro da class
	// Se der algum erro na validação ele vai vir por BindResult
	// BindingResult para verificar o resultado do @Valid
	@PostMapping("novo")
	public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result) {

		// Para não fazer isso deve ser usado o @Valid
		// As validações devem estar dentro do DTO
		// if(requisicao.getNomeProduto == null || requisicao.getNomeProduto.isEmpty())

		if (result.hasErrors()) {
			return "pedido/formulario";
		}

		Pedido pedido = requisicao.toPedido();
		pedidoRepository.save(pedido);

		// depois que salvar vai redirecionar para a pagina home
		return "redirect:/home";
	}

}
