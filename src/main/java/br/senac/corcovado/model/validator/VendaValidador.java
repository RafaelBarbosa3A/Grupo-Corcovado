package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.Venda;
import br.senac.corcovado.model.exception.VendaException;

/**
 *
 * @author wesley
 */
public class VendaValidador {

    public static void validar(Venda venda) throws VendaException{
        if (venda == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (venda.getClienteId() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (venda.getEnderecoId() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (venda.getDescontoId() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (venda.getTotal() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (venda.getPagamento().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (venda.getComprovante().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (venda.getPrazoEntrega().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (venda.getCodigoRastreamento().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
