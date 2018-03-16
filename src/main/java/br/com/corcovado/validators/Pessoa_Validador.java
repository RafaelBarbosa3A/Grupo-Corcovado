package br.com.corcovado.validators;

import br.com.corcovado.model.Pessoa;

/**
 *
 * @author wesley
 */
public class Pessoa_Validador {
    public static void validar(Pessoa pessoa) {
        if (pessoa == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pessoa.getNivel() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pessoa.getCargo() == null) {
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
