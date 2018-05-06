package br.senac.corcovado.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    @Column(name = "cliente_id") private Long clienteId; /*pessoa*/
    @Column(name = "endereco_id") private Long enderecoId;
    @Column(name = "desconto_id") private Long descontoId;    
    @Column(name = "status_id") private int statusId;
    @Column(name = "total") private Double total;
    @Column(name = "pagamento") private String pagamento;
    @Column(name = "comprovante") private String comprovante;
    @Column(name = "prazo_entrega") private String prazoEntrega;
    @Column(name = "codigo_rastreamento") private String codigoRastreamento;
    
    @OneToMany(targetEntity = ProdutoVendido.class, 
            mappedBy = "venda", 
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private Set<ProdutoVendido> produtoVendidos;
    
    @Column(name = "created_at") private Long createdAt;
    @Column(name = "updated_at") private Long updatedAt;
    @Column(name = "active") private boolean active;

    public Venda() {
        this.id = 0L;
        this.total = 0D;
        this.produtoVendidos = new HashSet<>();
    }

    public Venda(Long id, Long clienteId, Long enderecoId, Long descontoId, int statusId, Double total, String pagamento, String comprovante, String prazoEntrega, String codigoRastreamento, Set<ProdutoVendido> produtoVendidos, Long createdAt, Long updatedAt, boolean active) {
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
        this.produtoVendidos = produtoVendidos;
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

    public Set<ProdutoVendido> getProdutoVendidos() {
        return produtoVendidos;
    }
    public void setProdutoVendidos(Set<ProdutoVendido> produtoVendidos) {
        this.produtoVendidos = produtoVendidos;
    }
    
    public Long getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Venda{" + "id=" + id + ", clienteId=" + clienteId + ", enderecoId=" + enderecoId + ", descontoId=" + descontoId + ", statusId=" + statusId + ", total=" + total + ", pagamento=" + pagamento + ", comprovante=" + comprovante + ", prazoEntrega=" + prazoEntrega + ", codigoRastreamento=" + codigoRastreamento + ", produtoVendidos=" + produtoVendidos + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", active=" + active + '}';
    } 
}
