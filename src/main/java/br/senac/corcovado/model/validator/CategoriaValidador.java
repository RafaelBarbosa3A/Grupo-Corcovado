package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.Categoria;

/**
 *
 * @author weslet
 */
public class CategoriaValidador {

    public static void validar(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (categoria.getNome().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (categoria.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (categoria.getDepartamento() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
