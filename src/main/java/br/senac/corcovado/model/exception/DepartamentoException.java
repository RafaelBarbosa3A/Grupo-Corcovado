package br.senac.corcovado.model.exception;

import java.util.List;

/**
 *
 * @author wesley
 */
public class DepartamentoException extends Exception{
    private final List errors;

    public DepartamentoException(List errors) {
        this.errors = errors;
    }

    public DepartamentoException(List errors, String message) {
        super(message);
        this.errors = errors;
    }

    public List getErrors() {
        return errors;
    }
}
