package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.Desconto;
import br.senac.corcovado.model.exception.DescontoException;

/**
 *
 * @author wesley
 */
public class DescontoValidador {
    public static void validar(Desconto desconto) throws DescontoException{
        if (desconto == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (desconto.getDesconto().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (desconto.getPercentual() <= 0) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (desconto.getCodigoCupom().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
