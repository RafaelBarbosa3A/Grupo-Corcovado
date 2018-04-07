package br.senac.corcovado.model.repository;

import br.senac.corcovado.model.entity.Departamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author wesley
 */

@Transactional
public interface DepartamentoRepository extends CrudRepository<Departamento, Long> {
    
}
