package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public class Categoria extends DataBasicModel{
    private String nome;
    private String descricao;
    private Long departamento_id;

    public Categoria() {
    }

    public Categoria(String nome, String descricao, Long departamento_id) {
        this.nome = nome;
        this.descricao = descricao;
        this.departamento_id = departamento_id;
    }

    public Categoria(String nome, String descricao, Long departamento_id, Long id, boolean active) {
        super(id, active);
        this.nome = nome;
        this.descricao = descricao;
        this.departamento_id = departamento_id;
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

    public Long getDepartamento_id() {
        return departamento_id;
    }

    public void setDepartamento_id(Long departamento_id) {
        this.departamento_id = departamento_id;
    }
}
