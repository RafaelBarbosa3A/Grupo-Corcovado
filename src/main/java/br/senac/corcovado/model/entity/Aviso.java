package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public class Aviso extends DataBasicModel{
    private Long produto_id;
    private Long cliente_id;

    public Aviso() {
    }

    public Aviso(Long produto_id, Long cliente_id) {
        this.produto_id = produto_id;
        this.cliente_id = cliente_id;
    }

    public Aviso(Long produto_id, Long cliente_id, Long id, boolean active) {
        super(id, active);
        this.produto_id = produto_id;
        this.cliente_id = cliente_id;
    }

    public Long getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(Long produto_id) {
        this.produto_id = produto_id;
    }

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
    }
}
