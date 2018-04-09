package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.Sac;
import br.senac.corcovado.model.exception.SACException;

/**
 *
 * @author wesley
 */
public class SACValidador {

    public static void validar(Sac sac) throws SACException{
        if (sac == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (sac.getClienteId() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (sac.getContato().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (sac.getMensagem().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
