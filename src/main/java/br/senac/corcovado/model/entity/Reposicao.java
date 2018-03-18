package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public class Reposicao extends DataBasicModel{
    private String fornecedor;

    public Reposicao() {
    }

    public Reposicao(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Reposicao(String fornecedor, Long id, boolean active) {
        super(id, active);
        this.fornecedor = fornecedor;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }
}
