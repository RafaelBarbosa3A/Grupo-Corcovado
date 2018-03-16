package br.com.corcovado.validators;

import br.com.corcovado.model.Departamento;

/**
 *
 * @author wesley
 */
public class Departamento_Validador {
    public static void validar(Departamento departamento) {
        if (departamento == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (departamento.getNome().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (departamento.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
