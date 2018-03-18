package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.Resposta;

/**
 *
 * @author wesley
 */
public class Resposta_Validador {
    public static void validar(Resposta resposta) {
        if (resposta == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (resposta.getSac() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (resposta.getCliente() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (resposta.getMensagem().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
