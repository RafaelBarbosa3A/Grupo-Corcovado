package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.Resposta;

/**
 *
 * @author wesley
 */
public class RespostaValidador {
    public static void validar(Resposta resposta) {
        if (resposta == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (resposta.getSac_id() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (resposta.getCliente_id() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (resposta.getMensagem().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
