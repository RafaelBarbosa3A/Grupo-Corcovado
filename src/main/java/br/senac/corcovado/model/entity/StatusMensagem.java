/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.corcovado.model.entity;


/**
 *
 * @author Diego
 */
public enum StatusMensagem {
    NOVO("Novo"),
    ABERTO("Aberto"),
    ENCERRADO("Encerrado");
    
    private final String name;
    
    private StatusMensagem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
