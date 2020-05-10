package br.com.infoq.repository;

import br.com.infoq.model.Os;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OsRepository extends JpaRepository<Os,Long> {
    List<Os> findAllByAparelhoIgnoreCaseContaining(String aparelho);
    List<Os> findTop100ByOrderByIdDesc();
    List<Os> findAllByClienteNomeIgnoreCaseContaining(String clienteNome);
}
