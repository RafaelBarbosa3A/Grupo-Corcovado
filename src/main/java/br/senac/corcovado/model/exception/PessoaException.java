package br.senac.corcovado.model.exception;

import java.util.List;

/**
 *
 * @author wesley
 */
public class PessoaException extends Exception{
    private final List errors;

    public PessoaException(List errors) {
        this.errors = errors;
    }

    public PessoaException(List errors, String message) {
        super(message);
        this.errors = errors;
    }

    public List getErrors() {
        return errors;
    }
}
