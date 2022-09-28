package br.com.alura.mvc.mudi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;

//PedidoRepository é quem sabe se comunicar com o banco de dados
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	// Esta classe já vem com o findAll nativo pois tem exte extends JpaRepository

	// busca pelo status
	List<Pedido> findByStatus(StatusPedido aguardando);

}