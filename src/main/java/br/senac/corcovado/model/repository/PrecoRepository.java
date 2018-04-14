package br.senac.corcovado.model.repository;

import br.senac.corcovado.model.entity.Preco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author wesley
 */

@Transactional
public interface PrecoRepository extends CrudRepository<Preco, Long> {
    public Iterable<Preco> findAllByProdutoId(Long produtoId);
}
