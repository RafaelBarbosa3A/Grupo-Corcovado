package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.Reposicao;

/**
 *
 * @author wesley
 */
public class ReposicaoValidador {
    public static void validar(Reposicao reposicao) {
        if (reposicao == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (reposicao.getFornecedor().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
