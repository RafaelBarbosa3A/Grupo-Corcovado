package br.com.corcovado.model;

/**
 *
 * @author wesley
 */
public class Categoria extends DataBasic{
    private String nome;
    private String descricao;
    private Departamento departamento;

    public Categoria() {
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

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
