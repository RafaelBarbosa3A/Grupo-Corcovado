package br.senac.corcovado.model.repository;

import br.senac.corcovado.model.entity.Preco;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author wesley
 */
public interface PrecoRepository extends CrudRepository<Preco, Long> {
    public Optional<Preco> findPrecoById(long id);
    public Iterable<Preco> findAllByProdutoId(Long produtoId);
}
