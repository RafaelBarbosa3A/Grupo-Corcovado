package br.senac.corcovado.model.exception;

import java.util.List;

/**
 *
 * @author wesley
 */
public class DescontoException extends Exception{
    private final List errors;

    public DescontoException(List errors) {
        this.errors = errors;
    }

    public DescontoException(List errors, String message) {
        super(message);
        this.errors = errors;
    }

    public List getErrors() {
        return errors;
    }
}
