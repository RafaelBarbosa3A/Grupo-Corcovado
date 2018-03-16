package br.com.corcovado.model;

import java.math.BigDecimal;

/**
 *
 * @author wesley
 */
public class Preco extends DataBasic{
    private Produto produto;
    private BigDecimal preco;
    private Nivel nivel;

    public Preco() {
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }
    
    
}
