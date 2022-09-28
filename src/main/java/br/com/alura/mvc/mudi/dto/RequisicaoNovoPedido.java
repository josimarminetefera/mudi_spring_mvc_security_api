package br.com.alura.mvc.mudi.dto;

import javax.validation.constraints.NotBlank;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;

public class RequisicaoNovoPedido {

	// @NotBlank isso aqui vai retornar um erro assim
	// NotBlank.requisicaoNovoPedido.nomeProduto=campo deve ser preenchido
	@NotBlank // NotBlank.requisicaoNovoPedido.nomeProduto=nÃ£o pode estar em branco
	private String nomeProduto;

	@NotBlank
	private String urlProduto;

	@NotBlank
	private String urlImagem;
	private String descricao;

	public void valida() {
		// voce pode usar um metodo assim para validar os campos da tela
		// todas as lógicas de validação podem ficar aqui
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getUrlProduto() {
		return urlProduto;
	}

	public void setUrlProduto(String urlProduto) {
		this.urlProduto = urlProduto;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Pedido toPedido() {
		Pedido pedido = new Pedido();
		pedido.setDescricao(descricao);
		pedido.setNomeProduto(nomeProduto);
		pedido.setUrlImagem(urlImagem);
		pedido.setUrlProduto(urlProduto);
		pedido.setStatus(StatusPedido.AGUARDANDO);// setar o status padrão
		return pedido;
	}

}
