package br.senac.corcovado.model.exception;

import java.util.List;

/**
 *
 * @author wesley
 */
public class AvisoException extends Exception{
    private final List errors;

    public AvisoException(List errors) {
        this.errors = errors;
    }

    public AvisoException(List errors, String message) {
        super(message);
        this.errors = errors;
    }

    public List getErrors() {
        return errors;
    }
}
