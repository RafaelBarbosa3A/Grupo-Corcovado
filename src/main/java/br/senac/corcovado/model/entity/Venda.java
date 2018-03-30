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
public class Venda implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")private Long id;
    @Column(name = "cliente_id")private Long cliente_id;
    @Column(name = "endereco_id")private Long endereco_id;
    @Column(name = "desconto_id")private Long desconto_id;    
    @Column(name = "status_id")private Long status_id;
    @Column(name = "total")private Double total;
    @Column(name = "pagamento")private String pagamento;
    @Column(name = "comprovante")private String comprovante;
    @Column(name = "prazo_entrega")private String prazo_entrega;
    @Column(name = "codigo_rastreamento")private String codigo_rastreamento;
    @Column(name = "created_at")private GregorianCalendar created_at;
    @Column(name = "updated_at")private GregorianCalendar updated_at;
    @Column(name = "active")private boolean active;

    public Venda() {
        this.id = 0L;
    }

    public Venda(Long id, Long cliente_id, Long endereco_id, Long desconto_id, Long status_id, Double total, String pagamento, String comprovante, String prazo_entrega, String codigo_rastreamento, GregorianCalendar created_at, GregorianCalendar updated_at, boolean active) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.endereco_id = endereco_id;
        this.desconto_id = desconto_id;
        this.status_id = status_id;
        this.total = total;
        this.pagamento = pagamento;
        this.comprovante = comprovante;
        this.prazo_entrega = prazo_entrega;
        this.codigo_rastreamento = codigo_rastreamento;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public GregorianCalendar getCreated_at() {
        return created_at;
    }

    public void setCreated_at(GregorianCalendar created_at) {
        this.created_at = created_at;
    }

    public GregorianCalendar getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(GregorianCalendar updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public void setCreated_at(long timeInMillis) {
        this.created_at = new GregorianCalendar();
        this.created_at.setTimeInMillis(timeInMillis);
    }
    
    public void setUpdated_at(long timeInMillis) {
        this.updated_at = new GregorianCalendar();
        this.updated_at.setTimeInMillis(timeInMillis);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.cliente_id);
        hash = 59 * hash + Objects.hashCode(this.endereco_id);
        hash = 59 * hash + Objects.hashCode(this.desconto_id);
        hash = 59 * hash + Objects.hashCode(this.status_id);
        hash = 59 * hash + Objects.hashCode(this.total);
        hash = 59 * hash + Objects.hashCode(this.pagamento);
        hash = 59 * hash + Objects.hashCode(this.comprovante);
        hash = 59 * hash + Objects.hashCode(this.prazo_entrega);
        hash = 59 * hash + Objects.hashCode(this.codigo_rastreamento);
        hash = 59 * hash + Objects.hashCode(this.created_at);
        hash = 59 * hash + Objects.hashCode(this.updated_at);
        hash = 59 * hash + (this.active ? 1 : 0);
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
        if (this.active != other.active) {
            return false;
        }
        if (!Objects.equals(this.pagamento, other.pagamento)) {
            return false;
        }
        if (!Objects.equals(this.comprovante, other.comprovante)) {
            return false;
        }
        if (!Objects.equals(this.prazo_entrega, other.prazo_entrega)) {
            return false;
        }
        if (!Objects.equals(this.codigo_rastreamento, other.codigo_rastreamento)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.cliente_id, other.cliente_id)) {
            return false;
        }
        if (!Objects.equals(this.endereco_id, other.endereco_id)) {
            return false;
        }
        if (!Objects.equals(this.desconto_id, other.desconto_id)) {
            return false;
        }
        if (!Objects.equals(this.status_id, other.status_id)) {
            return false;
        }
        if (!Objects.equals(this.total, other.total)) {
            return false;
        }
        if (!Objects.equals(this.created_at, other.created_at)) {
            return false;
        }
        if (!Objects.equals(this.updated_at, other.updated_at)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Venda{" + "id=" + id + ", cliente_id=" + cliente_id + ", endereco_id=" + endereco_id + ", desconto_id=" + desconto_id + ", status_id=" + status_id + ", total=" + total + ", pagamento=" + pagamento + ", comprovante=" + comprovante + ", prazo_entrega=" + prazo_entrega + ", codigo_rastreamento=" + codigo_rastreamento + ", created_at=" + created_at + ", updated_at=" + updated_at + ", active=" + active + '}';
    }
}
