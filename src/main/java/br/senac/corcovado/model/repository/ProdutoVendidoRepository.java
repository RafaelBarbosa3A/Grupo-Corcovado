package br.senac.corcovado.model.repository;

import br.senac.corcovado.model.entity.ProdutoVendido;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author wesley
 */

@Transactional
public interface ProdutoVendidoRepository extends CrudRepository<ProdutoVendido, Long> {
    
    Iterable<ProdutoVendido> findByVendaId(Long vendaId);
    
    boolean existsByVendaIdAndProdutoId(Long vendaId, Long produtoId);
    Optional<ProdutoVendido> findByVendaIdAndProdutoId(Long vendaId, Long produtoId);
}
