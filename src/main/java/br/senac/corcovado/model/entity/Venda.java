package br.senac.corcovado.model.entity;

/**
 *
 * @author wesley
 */
public class Venda extends DataBasicModel{
    private Long cliente_id;
    private Long endereco_id;
    private Long desconto_id;    
    private Long status_id;
    private Double total;
    private String pagamento;
    private String comprovante;
    private String prazo_entrega;
    private String codigo_rastreamento;

    public Venda() {
    }

    public Venda(Long cliente_id, Long endereco_id, Long desconto_id, Long status_id, Double total, String pagamento, String comprovante, String prazo_entrega, String codigo_rastreamento) {
        this.cliente_id = cliente_id;
        this.endereco_id = endereco_id;
        this.desconto_id = desconto_id;
        this.status_id = status_id;
        this.total = total;
        this.pagamento = pagamento;
        this.comprovante = comprovante;
        this.prazo_entrega = prazo_entrega;
        this.codigo_rastreamento = codigo_rastreamento;
    }

    public Venda(Long cliente_id, Long endereco_id, Long desconto_id, Long status_id, Double total, String pagamento, String comprovante, String prazo_entrega, String codigo_rastreamento, Long id, boolean active) {
        super(id, active);
        this.cliente_id = cliente_id;
        this.endereco_id = endereco_id;
        this.desconto_id = desconto_id;
        this.status_id = status_id;
        this.total = total;
        this.pagamento = pagamento;
        this.comprovante = comprovante;
        this.prazo_entrega = prazo_entrega;
        this.codigo_rastreamento = codigo_rastreamento;
    }

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Long getEndereco_id() {
        return endereco_id;
    }

    public void setEndereco_id(Long endereco_id) {
        this.endereco_id = endereco_id;
    }

    public Long getDesconto_id() {
        return desconto_id;
    }

    public void setDesconto_id(Long desconto_id) {
        this.desconto_id = desconto_id;
    }

    public Long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Long status_id) {
        this.status_id = status_id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getComprovante() {
        return comprovante;
    }

    public void setComprovante(String comprovante) {
        this.comprovante = comprovante;
    }

    public String getPrazo_entrega() {
        return prazo_entrega;
    }

    public void setPrazo_entrega(String prazo_entrega) {
        this.prazo_entrega = prazo_entrega;
    }

    public String getCodigo_rastreamento() {
        return codigo_rastreamento;
    }

    public void setCodigo_rastreamento(String codigo_rastreamento) {
        this.codigo_rastreamento = codigo_rastreamento;
    }
}
