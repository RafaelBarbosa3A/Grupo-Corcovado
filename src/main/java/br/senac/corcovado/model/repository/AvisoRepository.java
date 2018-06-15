package br.senac.corcovado.model.repository;

import br.senac.corcovado.model.entity.Aviso;
import br.senac.corcovado.model.entity.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author wesley
 */

@Transactional
public interface AvisoRepository extends CrudRepository<Aviso, Long>{

    Iterable<Aviso>  findAllByPessoa(Pessoa pessoa);
    
}
