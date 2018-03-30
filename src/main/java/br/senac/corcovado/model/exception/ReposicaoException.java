package br.senac.corcovado.model.exception;

import java.util.List;

/**
 *
 * @author wesley
 */
public class ReposicaoException extends Exception{
    private final List errors;

    public ReposicaoException(List errors) {
        this.errors = errors;
    }

    public ReposicaoException(List errors, String message) {
        super(message);
        this.errors = errors;
    }

    public List getErrors() {
        return errors;
    }
}
