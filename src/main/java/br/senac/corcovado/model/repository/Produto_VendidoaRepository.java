package br.senac.corcovado.model.repository;

import br.senac.corcovado.model.entity.Produto_Vendido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author wesley
 */

@Transactional
public interface Produto_VendidoaRepository extends CrudRepository<Produto_Vendido, Long>{
    
}
