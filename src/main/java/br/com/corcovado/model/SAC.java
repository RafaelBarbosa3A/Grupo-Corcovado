package br.com.corcovado.model;

/**
 *
 * @author wesley
 */
public class SAC extends DataBasic{
    private Pessoa cliente;
    private String contato;
    private String mensagem;
    private String status_mensagem;

    public SAC() {
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
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
