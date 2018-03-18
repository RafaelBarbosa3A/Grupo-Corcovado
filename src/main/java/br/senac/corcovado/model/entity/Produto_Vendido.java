package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public class Produto_Vendido extends DataBasicModel{
    private Long produto_id;
    private Long venda_id;
    private Integer quantidade;
    private Double preco_total;

    public Produto_Vendido() {
    }

    public Produto_Vendido(Long produto_id, Long venda_id, Integer quantidade, Double preco_total) {
        this.produto_id = produto_id;
        this.venda_id = venda_id;
        this.quantidade = quantidade;
        this.preco_total = preco_total;
    }

    public Produto_Vendido(Long produto_id, Long venda_id, Integer quantidade, Double preco_total, Long id, boolean active) {
        super(id, active);
        this.produto_id = produto_id;
        this.venda_id = venda_id;
        this.quantidade = quantidade;
        this.preco_total = preco_total;
    }

    public Long getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(Long produto_id) {
        this.produto_id = produto_id;
    }

    public Long getVenda_id() {
        return venda_id;
    }

    public void setVenda_id(Long venda_id) {
        this.venda_id = venda_id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(Double preco_total) {
        this.preco_total = preco_total;
    }
}
