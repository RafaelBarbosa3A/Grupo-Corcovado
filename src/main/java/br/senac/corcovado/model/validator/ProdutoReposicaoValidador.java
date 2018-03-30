package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.Produto_Reposicao;
import br.senac.corcovado.model.exception.ProdutoReposicaoException;


/**
 *
 * @author wesley
 */
public class ProdutoReposicaoValidador {
    public static void validar(Produto_Reposicao pr) throws ProdutoReposicaoException{
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
