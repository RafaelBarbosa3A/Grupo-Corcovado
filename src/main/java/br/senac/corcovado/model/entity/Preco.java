package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public class Preco extends DataBasicModel{
    private Long produto_id;
    private Double preco;
    private Long nivel_id;

    public Preco() {
    }

    public Preco(Long produto_id, Double preco, Long nivel_id) {
        this.produto_id = produto_id;
        this.preco = preco;
        this.nivel_id = nivel_id;
    }

    public Preco(Long produto_id, Double preco, Long nivel_id, Long id, boolean active) {
        super(id, active);
        this.produto_id = produto_id;
        this.preco = preco;
        this.nivel_id = nivel_id;
    }

    public Long getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(Long produto_id) {
        this.produto_id = produto_id;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Long getNivel_id() {
        return nivel_id;
    }

    public void setNivel_id(Long nivel_id) {
        this.nivel_id = nivel_id;
    }
}
