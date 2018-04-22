package br.senac.corcovado.model.repository;

import br.senac.corcovado.model.entity.Produto;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author wesley
 */
public interface ProdutoRepository extends CrudRepository<Produto, Long>{
    public Optional<Produto> findProdutoById(long id);
}
