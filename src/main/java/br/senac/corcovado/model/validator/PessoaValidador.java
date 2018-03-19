package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.Pessoa;

/**
 *
 * @author wesley
 */
public class PessoaValidador {
    public static void validar(Pessoa pessoa) {
        if (pessoa == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pessoa.getNivel_id() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pessoa.getCargo_id() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pessoa.getNome().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }else if (pessoa.getDocumento().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }else if (pessoa.getSenha().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
