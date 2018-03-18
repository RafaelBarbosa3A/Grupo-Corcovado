package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.Endereco;

/**
 *
 * @author wesley
 */
public class Endereco_Validador {

    public static void validar(Endereco endereco) {
        if (endereco == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (endereco.getCliente() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (endereco.getRua().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (endereco.getNumero() <= 0) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (endereco.getBairro().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (endereco.getCidade().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (endereco.getEstado().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (endereco.getCep().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (endereco.getComplemento().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (endereco.getPrincipal().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
