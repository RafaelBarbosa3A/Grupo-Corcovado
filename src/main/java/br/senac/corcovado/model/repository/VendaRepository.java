package br.senac.corcovado.model.repository;

import br.senac.corcovado.model.entity.Pessoa;
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
    Optional<Venda> findByPessoaAndStatusId(Pessoa pessoa, int statusId);
}
