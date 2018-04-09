package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.ProdutoReposicao;
import br.senac.corcovado.model.exception.ProdutoReposicaoException;


/**
 *
 * @author wesley
 */
public class ProdutoReposicaoValidador {
    public static void validar(ProdutoReposicao pr) throws ProdutoReposicaoException{
        if (pr == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pr.getProdutoId() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pr.getReposicaoId() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pr.getQuantidade() <= 0) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
