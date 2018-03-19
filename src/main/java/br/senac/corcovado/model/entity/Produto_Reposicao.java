package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public class Produto_Reposicao extends DataBasicModel{
    private Long reposicao_id;
    private Long produto_id;
    private Integer quantidade;

    public Produto_Reposicao() {
    }

    public Produto_Reposicao(Long reposicao_id, Long produto_id, Integer quantidade) {
        this.reposicao_id = reposicao_id;
        this.produto_id = produto_id;
        this.quantidade = quantidade;
    }

    public Produto_Reposicao(Long reposicao_id, Long produto_id, Integer quantidade, Long id, boolean active) {
        super(id, active);
        this.reposicao_id = reposicao_id;
        this.produto_id = produto_id;
        this.quantidade = quantidade;
    }

    public Long getReposicao_id() {
        return reposicao_id;
    }

    public void setReposicao_id(Long reposicao_id) {
        this.reposicao_id = reposicao_id;
    }

    public Long getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(Long produto_id) {
        this.produto_id = produto_id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
