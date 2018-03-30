package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.Categoria;
import br.senac.corcovado.model.exception.CategoriaException;

/**
 *
 * @author weslet
 */
public class CategoriaValidador {

    public static void validar(Categoria categoria) throws CategoriaException{
        if (categoria == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (categoria.getNome().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (categoria.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (categoria.getDepartamento_id() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
