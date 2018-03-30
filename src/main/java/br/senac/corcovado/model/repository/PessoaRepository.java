package br.senac.corcovado.model.repository;

import br.senac.corcovado.model.entity.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author wesley
 */

@Transactional
public interface PessoaRepository extends CrudRepository<Pessoa, Long>{
    
}
