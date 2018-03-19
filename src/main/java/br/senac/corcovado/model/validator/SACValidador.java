package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.SAC;

/**
 *
 * @author wesley
 */
public class SACValidador {

    public static void validar(SAC sac) {
        if (sac == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (sac.getCliente_id() == null) {
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
