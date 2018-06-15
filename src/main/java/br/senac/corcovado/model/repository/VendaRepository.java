package br.senac.corcovado.model.repository;

import br.senac.corcovado.model.entity.Pessoa;
import br.senac.corcovado.model.entity.Status;
import br.senac.corcovado.model.entity.Venda;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author wesley
 */

@Transactional
public interface VendaRepository extends CrudRepository<Venda, Long>{
    Optional<Venda> findByPessoaAndStatus(Pessoa pessoa, Status status);
    
    Iterable<Venda> findAllByPessoaId(Long pessoaId);
    
    
    Iterable<Venda> findAllByStatusNot(Status status);
    Iterable<Venda> findAllByPessoaIdAndStatusNot(Long pessoaId, Status status);
}
