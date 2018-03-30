package br.senac.corcovado.model.exception;

import java.util.List;

/**
 *
 * @author wesley
 */
public class ProdutoException extends Exception{
    private final List errors;

    public ProdutoException(List errors) {
        this.errors = errors;
    }

    public ProdutoException(List errors, String message) {
        super(message);
        this.errors = errors;
    }

    public List getErrors() {
        return errors;
    }
}
