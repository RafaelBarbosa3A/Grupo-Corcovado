package br.senac.corcovado.model.exception;

import java.util.List;

/**
 *
 * @author wesle
 */
public class PrecoException extends Exception{
    private final List errors;

    public PrecoException(List errors) {
        this.errors = errors;
    }

    public PrecoException(List errors, String message) {
        super(message);
        this.errors = errors;
    }

    public List getErrors() {
        return errors;
    }
}
