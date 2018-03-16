package br.com.corcovado.validators;

import br.com.corcovado.model.SAC;

/**
 *
 * @author wesley
 */
public class SAC_Validador {

    public static void validar(SAC sac) {
        if (sac == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (sac.getCliente() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (sac.getContato().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (sac.getMensagem().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (sac.getStatus_mensagem().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
