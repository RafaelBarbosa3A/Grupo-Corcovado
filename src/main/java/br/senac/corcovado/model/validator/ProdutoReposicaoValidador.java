package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.Produto_Reposicao;


/**
 *
 * @author wesley
 */
public class ProdutoReposicaoValidador {
    public static void validar(Produto_Reposicao pr) {
        if (pr == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pr.getProduto_id() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pr.getReposicao_id() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pr.getQuantidade() <= 0) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
