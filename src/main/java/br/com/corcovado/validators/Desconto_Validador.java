package br.com.corcovado.validators;

import br.com.corcovado.model.Desconto;

/**
 *
 * @author wesley
 */
public class Desconto_Validador {
    public static void validar(Desconto desconto) {
        if (desconto == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (desconto.getDesconto().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (desconto.getPercentual() <= 0) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (desconto.getCod_cupom().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
