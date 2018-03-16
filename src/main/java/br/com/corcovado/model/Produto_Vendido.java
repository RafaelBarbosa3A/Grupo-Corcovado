package br.com.corcovado.model;

import java.math.BigDecimal;

/**
 *
 * @author wesley
 */
public class Produto_Vendido extends DataBasic{
    private Produto produto;
    private Venda venda;
    private int quantidade;
    private BigDecimal preco_total;

    public Produto_Vendido() {
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(BigDecimal preco_total) {
        this.preco_total = preco_total;
    }
}
