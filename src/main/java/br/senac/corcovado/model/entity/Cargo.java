package br.senac.corcovado.model.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author wesley
 */
public enum Cargo {
    OPERACIONAL("Operacional"),
    GERENTE("Gerente"),
    DIRETOR("Diretor");
    
    private final String nome;
    
    private Cargo(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }
}
