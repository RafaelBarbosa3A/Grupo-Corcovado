package br.senac.corcovado.model.exception;

import java.util.List;

/**
 *
 * @author wesley
 */
public class SACException extends Exception{
    private final List errors;

    public SACException(List errors) {
        this.errors = errors;
    }

    public SACException(List errors, String message) {
        super(message);
        this.errors = errors;
    }

    public List getErrors() {
        return errors;
    }
}
