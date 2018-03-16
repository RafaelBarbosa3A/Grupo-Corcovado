package br.com.corcovado.model;

/**
 *
 * @author wesley
 */
public class Departamento extends DataBasic{
    private String nome;
    private String descricao;

    public Departamento() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
