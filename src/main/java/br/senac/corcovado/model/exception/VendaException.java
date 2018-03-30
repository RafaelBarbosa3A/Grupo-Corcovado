package br.senac.corcovado.model.exception;

import java.util.List;

/**
 *
 * @author wesley
 */
public class VendaException extends Exception{
    private final List errors;

    public VendaException(List errors) {
        this.errors = errors;
    }

    public VendaException(List errors, String message) {
        super(message);
        this.errors = errors;
    }

    public List getErrors() {
        return errors;
    }
}
