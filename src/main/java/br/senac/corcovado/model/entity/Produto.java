package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public class Produto extends DataBasicModel{
    private String nome;
    private String descricao;
    private String fabricante;
    private String codigo;
    private Integer estoque;
    private Integer reservado;
    private Long categoria_id;

    public Produto() {
    }

    public Produto(String nome, String descricao, String fabricante, String codigo, Integer estoque, Integer reservado, Long categoria_id) {
        this.nome = nome;
        this.descricao = descricao;
        this.fabricante = fabricante;
        this.codigo = codigo;
        this.estoque = estoque;
        this.reservado = reservado;
        this.categoria_id = categoria_id;
    }

    public Produto(String nome, String descricao, String fabricante, String codigo, Integer estoque, Integer reservado, Long categoria_id, Long id, boolean active) {
        super(id, active);
        this.nome = nome;
        this.descricao = descricao;
        this.fabricante = fabricante;
        this.codigo = codigo;
        this.estoque = estoque;
        this.reservado = reservado;
        this.categoria_id = categoria_id;
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

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Integer getReservado() {
        return reservado;
    }

    public void setReservado(Integer reservado) {
        this.reservado = reservado;
    }

    public Long getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(Long categoria_id) {
        this.categoria_id = categoria_id;
    }
}
