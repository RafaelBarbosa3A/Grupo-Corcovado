package br.senac.corcovado.model.exception;

import java.util.List;

/**
 *
 * @author wesley
 */
public class EnderecoException extends Exception{
    private final List errors;

    public EnderecoException(List errors) {
        this.errors = errors;
    }

    public EnderecoException(List errors, String message) {
        super(message);
        this.errors = errors;
    }

    public List getErrors() {
        return errors;
    }
}
