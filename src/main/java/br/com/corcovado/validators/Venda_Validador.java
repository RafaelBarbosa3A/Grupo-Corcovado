package br.com.corcovado.validators;

import br.com.corcovado.model.Venda;

/**
 *
 * @author wesley
 */
public class Venda_Validador {

    public static void validar(Venda venda) {
        if (venda == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (venda.getCliente() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (venda.getEndereco() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (venda.getDesconto() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (venda.getTotal() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (venda.getStatus().toString().isEmpty()) {
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
