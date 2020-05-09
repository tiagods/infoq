package br.com.infoq.repository;

import br.com.infoq.model.Os;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OsRepository extends JpaRepository<Os,Long> {
}
