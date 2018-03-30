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
        } else if (venda.getCliente_id() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (venda.getEndereco_id() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (venda.getDesconto_id() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (venda.getTotal() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (venda.getStatus_id().toString().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (venda.getPagamento().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (venda.getComprovante().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (venda.getPrazo_entrega().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (venda.getCodigo_rastreamento().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
