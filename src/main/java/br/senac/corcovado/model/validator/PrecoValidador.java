package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.Preco;
import br.senac.corcovado.model.exception.PrecoException;

/**
 *
 * @author wesley
 */
public class PrecoValidador {
    public static void validar(Preco preco) throws PrecoException{
        if (preco == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (preco.getProdutoId() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (preco.getPreco() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (preco.getNivelId() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
