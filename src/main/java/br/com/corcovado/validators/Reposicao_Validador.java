package br.com.corcovado.validators;

import br.com.corcovado.model.Reposicao;

/**
 *
 * @author wesley
 */
public class Reposicao_Validador {
    public static void validar(Reposicao reposicao) {
        if (reposicao == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (reposicao.getFornecedor().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
