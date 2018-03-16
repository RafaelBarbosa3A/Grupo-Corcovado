package br.com.corcovado.model;

/**
 *
 * @author wesley
 */
public class Resposta extends DataBasic{
    private SAC sac;
    private String mensagem;
    private Pessoa cliente;

    public Resposta() {
    }

    public SAC getSac() {
        return sac;
    }

    public void setSac(SAC sac) {
        this.sac = sac;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }
}
