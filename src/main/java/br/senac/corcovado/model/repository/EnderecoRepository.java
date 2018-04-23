package br.senac.corcovado.model.repository;

import br.senac.corcovado.model.entity.Endereco;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author wesley
 */
public interface EnderecoRepository extends CrudRepository<Endereco, Long> {
    public Iterable<Endereco> findAllByPessoaId(long pessoaId);
}
