package br.senac.corcovado.model.repository;

import br.senac.corcovado.model.entity.ProdutoReposicao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author wesle
 */

@Transactional
public interface ProdutoReposicaoRepository extends CrudRepository<ProdutoReposicao, Long>{
    
}
