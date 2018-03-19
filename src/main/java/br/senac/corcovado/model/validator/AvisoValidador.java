package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.Aviso;

/**
 *
 * @author wesley
 */
public class AvisoValidador {
    public static void validar(Aviso aviso) {
        if (aviso == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (aviso.getCliente_id() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (aviso.getProduto_id() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
