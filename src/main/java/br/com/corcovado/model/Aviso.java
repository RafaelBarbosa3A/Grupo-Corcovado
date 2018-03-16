package br.com.corcovado.model;

/**
 *
 * @author wesley
 */
public class Aviso extends DataBasic{
    private Produto produto;
    private Pessoa cliente;

    public Aviso() {
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }
}
