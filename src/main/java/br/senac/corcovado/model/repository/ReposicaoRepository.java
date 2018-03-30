package br.senac.corcovado.model.repository;

import br.senac.corcovado.model.entity.Reposicao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author wesley
 */

@Transactional
public interface ReposicaoRepository extends CrudRepository<Reposicao, Long>{
    
}
