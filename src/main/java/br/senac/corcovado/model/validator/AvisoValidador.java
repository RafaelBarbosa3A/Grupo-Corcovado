package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.Aviso;
import br.senac.corcovado.model.exception.AvisoException;

/**
 *
 * @author wesley
 */
public class AvisoValidador {
    public static void validar(Aviso aviso) throws AvisoException{
        if (aviso == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (aviso.getClienteId() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (aviso.getProdutoId() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
