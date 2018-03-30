package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.Reposicao;
import br.senac.corcovado.model.exception.ReposicaoException;

/**
 *
 * @author wesley
 */
public class ReposicaoValidador {
    public static void validar(Reposicao reposicao) throws ReposicaoException{
        if (reposicao == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (reposicao.getFornecedor().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
