package br.senac.corcovado.model.repository;

import br.senac.corcovado.model.entity.Endereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author wesley
 */

@Transactional
public interface EnderecoRepository extends CrudRepository<Endereco, Long>{
    
}
