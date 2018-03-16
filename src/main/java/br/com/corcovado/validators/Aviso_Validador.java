package br.com.corcovado.validators;

import br.com.corcovado.model.Aviso;

/**
 *
 * @author wesley
 */
public class Aviso_Validador {
    public static void validar(Aviso aviso) {
        if (aviso == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (aviso.getCliente() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (aviso.getProduto() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
