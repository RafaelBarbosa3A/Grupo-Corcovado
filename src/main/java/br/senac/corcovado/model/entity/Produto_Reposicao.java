package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public class Produto_Reposicao extends DataBasic{
    private Reposicao reposicao;
    private Produto produto;
    private int quantidade;

    public Produto_Reposicao() {
    }

    public Reposicao getReposicao() {
        return reposicao;
    }

    public void setReposicao(Reposicao reposicao) {
        this.reposicao = reposicao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
