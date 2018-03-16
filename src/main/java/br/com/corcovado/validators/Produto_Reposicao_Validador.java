package br.com.corcovado.validators;

import br.com.corcovado.model.Produto_Reposicao;


/**
 *
 * @author wesley
 */
public class Produto_Reposicao_Validador {
    public static void validar(Produto_Reposicao pr) {
        if (pr == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pr.getProduto() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pr.getReposicao() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pr.getQuantidade() <= 0) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
