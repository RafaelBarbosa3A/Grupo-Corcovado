package br.senac.corcovado.model.repository;

import br.senac.corcovado.model.entity.Departamento;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author wesley
 */

public interface DepartamentoRepository extends CrudRepository<Departamento, Long> {
    public Optional<Departamento> findDepartamentoById(long id);
}
