package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.Departamento;
import br.senac.corcovado.model.exception.DepartamentoException;

/**
 *
 * @author wesley
 */
public class DepartamentoValidador {
    public static void validar(Departamento departamento) throws DepartamentoException{
        if (departamento == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (departamento.getNome().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (departamento.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
