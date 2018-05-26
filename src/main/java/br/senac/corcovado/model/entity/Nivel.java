package br.senac.corcovado.model.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author wesley
 */
public enum Nivel {
    DESCONTO("Desconto"),
    BASIC("Basic"),
    SILVER("Silver"),
    GOLD("Gold"),
    PLATINUM("Platinum"),
    BLACK("Black");
    
    private final String nome;
    
    private Nivel(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
