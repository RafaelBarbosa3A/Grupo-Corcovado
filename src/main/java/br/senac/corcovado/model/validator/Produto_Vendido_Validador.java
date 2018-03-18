package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.Produto_Vendido;

/**
 *
 * @author wesley
 */
public class Produto_Vendido_Validador {
    public static void validar(Produto_Vendido pv) {
        if (pv == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pv.getProduto() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pv.getVenda() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pv.getPreco_total() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pv.getQuantidade() <= 0) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
