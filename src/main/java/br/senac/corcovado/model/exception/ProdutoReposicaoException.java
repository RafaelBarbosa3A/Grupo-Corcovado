package br.senac.corcovado.model.exception;

import java.util.List;

/**
 *
 * @author wesley
 */
public class ProdutoReposicaoException extends Exception{
    private final List errors;

    public ProdutoReposicaoException(List errors) {
        this.errors = errors;
    }

    public ProdutoReposicaoException(List errors, String message) {
        super(message);
        this.errors = errors;
    }

    public List getErrors() {
        return errors;
    }
}
