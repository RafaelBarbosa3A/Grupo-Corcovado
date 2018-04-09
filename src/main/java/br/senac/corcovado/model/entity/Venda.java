package br.senac.corcovado.model.entity;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author wesley
 */

@Entity
@Table(name = "venda")
public class Venda implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;
    @Column(name = "cliente_id") private Long clienteId;
    @Column(name = "endereco_id") private Long enderecoId;
    @Column(name = "desconto_id") private Long descontoId;    
    @Column(name = "status_id") private int statusId;
    @Column(name = "total") private Double total;
    @Column(name = "pagamento") private String pagamento;
    @Column(name = "comprovante") private String comprovante;
    @Column(name = "prazo_entrega") private String prazoEntrega;
    @Column(name = "codigo_rastreamento") private String codigoRastreamento;
    @Column(name = "created_at") private GregorianCalendar createdAt;
    @Column(name = "updated_at") private GregorianCalendar updatedAt;
    @Column(name = "active") private boolean active;

    public Venda() {
        this.id = 0L;
    }

    public Venda(Long id, Long clienteId, Long enderecoId, Long descontoId, int statusId, Double total, String pagamento, String comprovante, String prazoEntrega, String codigoRastreamento, GregorianCalendar createdAt, GregorianCalendar updatedAt, boolean active) {
        this.id = id;
        this.clienteId = clienteId;
        this.enderecoId = enderecoId;
        this.descontoId = descontoId;
        this.statusId = statusId;
        this.total = total;
        this.pagamento = pagamento;
        this.comprovante = comprovante;
        this.prazoEntrega = prazoEntrega;
        this.codigoRastreamento = codigoRastreamento;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
    }

    public Long getDescontoId() {
        return descontoId;
    }

    public void setDescontoId(Long descontoId) {
        this.descontoId = descontoId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
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

    public String getPrazoEntrega() {
        return prazoEntrega;
    }

    public void setPrazoEntrega(String prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }

    public String getCodigoRastreamento() {
        return codigoRastreamento;
    }

    public void setCodigoRastreamento(String codigoRastreamento) {
        this.codigoRastreamento = codigoRastreamento;
    }

    public GregorianCalendar getCreated_at() {
        return createdAt;
    }

    public void setCreated_at(GregorianCalendar createdAt) {
        this.createdAt = createdAt;
    }

    public GregorianCalendar getUpdated_at() {
        return updatedAt;
    }

    public void setUpdated_at(GregorianCalendar updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public void setCreated_at(long timeInMillis) {
        this.createdAt = new GregorianCalendar();
        this.createdAt.setTimeInMillis(timeInMillis);
    }
    
    public void setUpdated_at(long timeInMillis) {
        this.updatedAt = new GregorianCalendar();
        this.updatedAt.setTimeInMillis(timeInMillis);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.clienteId);
        hash = 29 * hash + Objects.hashCode(this.enderecoId);
        hash = 29 * hash + Objects.hashCode(this.descontoId);
        hash = 29 * hash + this.statusId;
        hash = 29 * hash + Objects.hashCode(this.total);
        hash = 29 * hash + Objects.hashCode(this.pagamento);
        hash = 29 * hash + Objects.hashCode(this.comprovante);
        hash = 29 * hash + Objects.hashCode(this.prazoEntrega);
        hash = 29 * hash + Objects.hashCode(this.codigoRastreamento);
        hash = 29 * hash + Objects.hashCode(this.createdAt);
        hash = 29 * hash + Objects.hashCode(this.updatedAt);
        hash = 29 * hash + (this.active ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Venda other = (Venda) obj;
        if (this.statusId != other.statusId) {
            return false;
        }
        if (this.active != other.active) {
            return false;
        }
        if (!Objects.equals(this.pagamento, other.pagamento)) {
            return false;
        }
        if (!Objects.equals(this.comprovante, other.comprovante)) {
            return false;
        }
        if (!Objects.equals(this.prazoEntrega, other.prazoEntrega)) {
            return false;
        }
        if (!Objects.equals(this.codigoRastreamento, other.codigoRastreamento)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.clienteId, other.clienteId)) {
            return false;
        }
        if (!Objects.equals(this.enderecoId, other.enderecoId)) {
            return false;
        }
        if (!Objects.equals(this.descontoId, other.descontoId)) {
            return false;
        }
        if (!Objects.equals(this.total, other.total)) {
            return false;
        }
        if (!Objects.equals(this.createdAt, other.createdAt)) {
            return false;
        }
        if (!Objects.equals(this.updatedAt, other.updatedAt)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Venda{" + "id=" + id + ", clienteId=" + clienteId + ", enderecoId=" + enderecoId + ", descontoId=" + descontoId + ", statusId=" + statusId + ", total=" + total + ", pagamento=" + pagamento + ", comprovante=" + comprovante + ", prazoEntrega=" + prazoEntrega + ", codigoRastreamento=" + codigoRastreamento + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", active=" + active + '}';
    }
}
