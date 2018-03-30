package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.Produto_Vendido;
import br.senac.corcovado.model.exception.ProdutoVendidoException;

/**
 *
 * @author wesley
 */
public class ProdutoVendidoValidador {
    public static void validar(Produto_Vendido pv) throws ProdutoVendidoException{
        if (pv == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pv.getProduto_id() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pv.getVenda_id() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pv.getPreco_total() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pv.getQuantidade() <= 0) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
