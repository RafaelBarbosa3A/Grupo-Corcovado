package br.com.corcovado.validators;

import br.com.corcovado.model.Categoria;

/**
 *
 * @author weslet
 */
public class Categoria_Validador {

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
