/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.corcovado.controller.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class Carrinho {
    public long vendaId;
    public List<ItemCarrinho> itens;

    public Carrinho() {
        this.vendaId = 0L;
        this.itens = new ArrayList<>();
    }

    public long getVendaId() {
        return vendaId;
    }

    public void setVendaId(long vendaId) {
        this.vendaId = vendaId;
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinho> itens) {
        this.itens = itens;
    }

    
    
    
    @Override
    public String toString() {
        return "Carrinho {" + "vendaId=" + vendaId + ", itens=" + itens + "}";
    }
}
