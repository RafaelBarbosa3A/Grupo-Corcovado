package br.senac.corcovado.model.repository;

import br.senac.corcovado.model.entity.Categoria;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author wesley
 */

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
    public Optional<Categoria> findCategoriaById(long id);
    // public Iterable<Categoria> findAllByDepartamentoId(long departamento_id);
}
