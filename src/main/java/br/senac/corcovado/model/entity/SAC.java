package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public class SAC extends DataBasicModel{
    private Long cliente_id;
    private String contato;
    private String mensagem;
    private String status_mensagem;

    public SAC() {
    }

    public SAC(Long cliente, String contato, String mensagem, String status_mensagem) {
        this.cliente_id = cliente;
        this.contato = contato;
        this.mensagem = mensagem;
        this.status_mensagem = status_mensagem;
    }

    public SAC(Long cliente, String contato, String mensagem, String status_mensagem, Long id, boolean active) {
        super(id, active);
        this.cliente_id = cliente;
        this.contato = contato;
        this.mensagem = mensagem;
        this.status_mensagem = status_mensagem;
    }

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente) {
        this.cliente_id = cliente;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getStatus_mensagem() {
        return status_mensagem;
    }

    public void setStatus_mensagem(String status_mensagem) {
        this.status_mensagem = status_mensagem;
    }
}
