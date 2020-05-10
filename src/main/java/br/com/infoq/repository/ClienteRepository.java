package br.com.infoq.repository;

import br.com.infoq.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findAllByNomeIgnoreCaseContaining(String name);
    List<Cliente> findTop100ByOrderByIdDesc();
}
