package br.senac.corcovado.model.validator;

import br.senac.corcovado.model.entity.Produto;

/**
 *
 * @author wesley
 */
public class ProdutoValidador {

    public static void validar(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (produto.getCategoria_id() == null) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (produto.getNome().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (produto.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (produto.getFabricante().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (produto.getCodigo().isEmpty()) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (produto.getEstoque() <= 0) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        } else if (produto.getReservado() <= 0) {
            throw new IllegalArgumentException("valor informado invalido ou nulo");
        }
    }
}
