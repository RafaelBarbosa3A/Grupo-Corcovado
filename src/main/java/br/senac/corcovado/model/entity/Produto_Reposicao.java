package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public class Produto_Reposicao extends DataBasicModel{
    private Long reposicao;
    private Integer produto;
    private Long quantidade;

    public Produto_Reposicao() {
    }

    public Produto_Reposicao(Long reposicao, Integer produto, Long quantidade) {
        this.reposicao = reposicao;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto_Reposicao(Long reposicao, Integer produto, Long quantidade, Long id, boolean active) {
        super(id, active);
        this.reposicao = reposicao;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Long getReposicao() {
        return reposicao;
    }

    public void setReposicao(Long reposicao) {
        this.reposicao = reposicao;
    }

    public Integer getProduto() {
        return produto;
    }

    public void setProduto(Integer produto) {
        this.produto = produto;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }
}
