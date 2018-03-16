package br.com.corcovado.model;

/**
 *
 * @author wesley
 */
public class Produto extends DataBasic{
    private String nome;
    private String descricao;
    private String fabricante;
    private String codigo;
    private int estoque;
    private int reservado;
    private Categoria categoria;

    public Produto() {
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

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public int getReservado() {
        return reservado;
    }

    public void setReservado(int reservado) {
        this.reservado = reservado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }    
}
