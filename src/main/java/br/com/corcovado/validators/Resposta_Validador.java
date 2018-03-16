package br.com.corcovado.validators;

import br.com.corcovado.model.Resposta;

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
