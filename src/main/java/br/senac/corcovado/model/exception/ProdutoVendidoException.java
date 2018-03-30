package br.senac.corcovado.model.exception;

import java.util.List;

/**
 *
 * @author wesley
 */
public class ProdutoVendidoException extends Exception{
    private final List errors;

    public ProdutoVendidoException(List errors) {
        this.errors = errors;
    }

    public ProdutoVendidoException(List errors, String message) {
        super(message);
        this.errors = errors;
    }

    public List getErrors() {
        return errors;
    }
}
