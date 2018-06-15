package br.senac.corcovado.model.repository;

import br.senac.corcovado.model.entity.Sac;
import br.senac.corcovado.model.entity.StatusMensagem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author wesley
 */

@Transactional
public interface SacRepository extends CrudRepository<Sac, Long> {
    Iterable<Sac> findAllByPessoaId(Long pessoaId);
    
    Iterable<Sac> findAllByStatusMensagemNotOrderByStatusMensagemDesc(StatusMensagem statusMensagem);
    
    
    Iterable<Sac> findAllByPessoaIdAndStatusMensagemNotOrderByStatusMensagemDesc(Long pessoaId, StatusMensagem statusMensagem);
}
