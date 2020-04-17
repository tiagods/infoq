package br.com.infoq.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.infoq.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	List<Cliente> findAllByNome(String name);

}
