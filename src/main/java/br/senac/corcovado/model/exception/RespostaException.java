package br.senac.corcovado.model.exception;

import java.util.List;

/**
 *
 * @author wesley
 */
public class RespostaException extends Exception{
    private final List errors;

    public RespostaException(List errors) {
        this.errors = errors;
    }

    public RespostaException(List errors, String message) {
        super(message);
        this.errors = errors;
    }

    public List getErrors() {
        return errors;
    }
}
