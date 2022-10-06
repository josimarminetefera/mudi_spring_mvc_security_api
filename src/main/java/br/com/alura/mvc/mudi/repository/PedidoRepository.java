package br.com.alura.mvc.mudi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;

//PedidoRepository � quem sabe se comunicar com o banco de dados
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	// Esta classe ja vem com o findAll nativo pois tem exte extends JpaRepository

	// busca pelo status
	List<Pedido> findByStatus(StatusPedido aguardando);

	//busca os pedidos por usuário
	@Query("select p from Pedido p join p.user u where u.username = :username")
	List<Pedido> findAllByUsuario(@Param("username") String username);

}