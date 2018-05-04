/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.corcovado.model.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author Diego
 */
public enum StatusMensagem {
        
    ABERTO(1, "Aberto"),
    EM_PROCESSO(2, "Em Processo"),
    ENCERRADO(3, "Encerrado");
    
    private final int id;
    @NotEmpty(message = "Favor digitar uma mensagem")
    @Size(min=1,max=255,message="Favor digitar uma mensagem entre 1 á 255 letras")
    private final String name;
    
    private StatusMensagem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    
    @Override public String toString() {
        return name;
    }

    public static StatusMensagem valueOf(int id) {
        switch (id) {
            case (1): return ABERTO;
            case (2): return EM_PROCESSO;
            case (3): return ENCERRADO;
            default: return null;
        }
    }
}
