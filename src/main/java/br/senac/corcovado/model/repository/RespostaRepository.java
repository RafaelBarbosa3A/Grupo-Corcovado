package br.senac.corcovado.model.repository;

import br.senac.corcovado.model.entity.Resposta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author wesley
 */

@Transactional
public interface RespostaRepository extends CrudRepository<Resposta, Long>{
    
}
