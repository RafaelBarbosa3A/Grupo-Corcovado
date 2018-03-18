package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public class Pessoa extends DataBasicModel{
    private String nome;
    private String documento;
    private String login;
    private String senha;
    private Long nivel_id;
    private Long cargo_id;

    public Pessoa() {
    }

    public Pessoa(String nome, String documento, String login, String senha, Long nivel_id, Long cargo_id) {
        this.nome = nome;
        this.documento = documento;
        this.login = login;
        this.senha = senha;
        this.nivel_id = nivel_id;
        this.cargo_id = cargo_id;
    }

    public Pessoa(String nome, String documento, String login, String senha, Long nivel_id, Long cargo_id, Long id, boolean active) {
        super(id, active);
        this.nome = nome;
        this.documento = documento;
        this.login = login;
        this.senha = senha;
        this.nivel_id = nivel_id;
        this.cargo_id = cargo_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getNivel_id() {
        return nivel_id;
    }

    public void setNivel_id(Long nivel_id) {
        this.nivel_id = nivel_id;
    }

    public Long getCargo_id() {
        return cargo_id;
    }

    public void setCargo_id(Long cargo_id) {
        this.cargo_id = cargo_id;
    }
}
