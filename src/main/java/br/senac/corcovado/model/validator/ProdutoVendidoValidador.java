package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.ProdutoVendido;
import br.senac.corcovado.model.exception.ProdutoVendidoException;

/**
 *
 * @author wesley
 */
public class ProdutoVendidoValidador {
    public static void validar(ProdutoVendido pv) throws ProdutoVendidoException{
        if (pv == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pv.getProdutoId() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pv.getVendaId() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pv.getPrecoTotal() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (pv.getQuantidade() <= 0) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
