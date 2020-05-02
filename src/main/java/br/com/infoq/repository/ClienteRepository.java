package br.com.infoq.repository;

import br.com.infoq.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	List<Cliente> findAllByNomeIgnoreCaseStartsWith(String name);
        
        @Override
        @Query("from Cliente c order by c.id desc")
        List<Cliente> findAll();
}
