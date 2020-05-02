package br.com.infoq.repository;

import br.com.infoq.model.Os;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OsRepository extends JpaRepository<Os,Long>{
        @Override
        @Query("from Os c order by c.id desc")
        List<Os> findAll();
}
