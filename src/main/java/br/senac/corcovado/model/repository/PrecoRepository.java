package br.senac.corcovado.model.repository;

import br.senac.corcovado.model.entity.Preco;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author wesley
 */
public interface PrecoRepository extends CrudRepository<Preco, Long> {
    public Iterable<Preco> findAllByProdutoId(long produtoId);
}
