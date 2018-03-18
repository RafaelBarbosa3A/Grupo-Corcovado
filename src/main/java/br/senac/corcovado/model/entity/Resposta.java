package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public class Resposta extends DataBasicModel{
    private Long sac_id;
    private String mensagem;
    private Long cliente_id;

    public Resposta() {
    }

    public Resposta(Long sac_id, String mensagem, Long cliente_id) {
        this.sac_id = sac_id;
        this.mensagem = mensagem;
        this.cliente_id = cliente_id;
    }

    public Resposta(Long sac_id, String mensagem, Long cliente_id, Long id, boolean active) {
        super(id, active);
        this.sac_id = sac_id;
        this.mensagem = mensagem;
        this.cliente_id = cliente_id;
    }

    public Long getSac_id() {
        return sac_id;
    }

    public void setSac_id(Long sac_id) {
        this.sac_id = sac_id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
    }
}
