package br.senac.corcovado.model.exception;

import java.util.List;

/**
 *
 * @author wesley
 */
public class CategoriaException extends Exception{
    private final List errors;

    public CategoriaException(List errors) {
        this.errors = errors;
    }

    public CategoriaException(List errors, String message) {
        super(message);
        this.errors = errors;
    }

    public List getErrors() {
        return errors;
    }
}
