package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public class Reposicao extends DataBasic{
    private String fornecedor;

    public Reposicao() {
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }
}