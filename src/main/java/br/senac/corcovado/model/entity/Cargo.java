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
    
    @NotEmpty(message = "Favor digitar um nome")
    @Size(min=1,max=255,message="Favor digitar um nome entre 1 á 255 letras")
    private final String nome;
    
    private Cargo(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }
}
