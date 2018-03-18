package br.senac.corcovado.model.entity;

import java.math.BigDecimal;

/**
 *
 * @author wesley
 */
public class Venda extends DataBasic{
    private Pessoa cliente;
    private Endereco endereco;
    private Desconto desconto;
    private BigDecimal total;
    private Status status;
    private String pagamento;
    private String comprovante;
    private String prazo_entrega;
    private String codigo_rastreamento;

    public Venda() {
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Desconto getDesconto() {
        return desconto;
    }

    public void setDesconto(Desconto desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
