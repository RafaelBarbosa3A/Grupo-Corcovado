/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.corcovado.controller.adapter;

import java.io.Serializable;

/**
 *
 * @author Diego
 */
public class ItemCarrinho implements Serializable {
    public long produtoId;
    public int quantidade;

    public ItemCarrinho() {
        this.produtoId = 0L;
        this.quantidade = 0;
    }

    public long getProdutoId() {
        return produtoId;
    }
    public void setProdutoId(long produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    @Override
    public String toString() {
        return "ItemCarrinho {" + "produtoId=" + produtoId + ", quantidade=" + quantidade + "}";
    }
}
