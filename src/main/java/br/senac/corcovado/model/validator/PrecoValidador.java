package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.Preco;

/**
 *
 * @author wesley
 */
public class PrecoValidador {
    public static void validar(Preco preco) {
        if (preco == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (preco.getProduto_id() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (preco.getPreco() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (preco.getNivel_id() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
