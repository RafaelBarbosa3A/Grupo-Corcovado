package br.com.corcovado.validators;

import br.com.corcovado.model.Preco;

/**
 *
 * @author wesley
 */
public class Preco_Validador {
    public static void validar(Preco preco) {
        if (preco == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (preco.getProduto() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (preco.getPreco() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (preco.getNivel() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
