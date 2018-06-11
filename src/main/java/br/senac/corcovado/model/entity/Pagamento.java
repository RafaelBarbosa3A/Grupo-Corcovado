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
public enum Pagamento {
    APAGAR("Pagar na entrega"), // pagar na entrega
    CREDITO("Cartão de crédito"), // crédito
    DEBITO("Cartão de débito"), // débito
    BOLETO("Boleto"); // boleto

    private final String nome;
    
    private Pagamento(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
