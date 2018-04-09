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

// TODO

@Entity
@Table(name = "produto_vendido")
public class ProdutoVendido implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;
    @Column(name = "produto_id") private Long produtoId;
    @Column(name = "venda_id") private Long vendaId;
    @Column(name = "quantidade") private Integer quantidade;
    @Column(name = "preco_total") private Double precoTotal;
    @Column(name = "created_at") private GregorianCalendar createdAt;
    @Column(name = "updated_at") private GregorianCalendar updatedAt;
    @Column(name = "active") private boolean active;    

    public ProdutoVendido() {
        this.id = 0L;
    }

    public ProdutoVendido(Long id, Long produto_id, Long venda_id, Integer quantidade, Double precoTotal, GregorianCalendar createdAt, GregorianCalendar updatedAt, boolean active) {
        this.id = id;
        this.produtoId = produto_id;
        this.vendaId = venda_id;
        this.quantidade = quantidade;
        this.precoTotal = precoTotal;
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

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Long getVendaId() {
        return vendaId;
    }

    public void setVendaId(Long vendaId) {
        this.vendaId = vendaId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
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
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.produtoId);
        hash = 29 * hash + Objects.hashCode(this.vendaId);
        hash = 29 * hash + Objects.hashCode(this.quantidade);
        hash = 29 * hash + Objects.hashCode(this.precoTotal);
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
        final ProdutoVendido other = (ProdutoVendido) obj;
        if (this.active != other.active) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.produtoId, other.produtoId)) {
            return false;
        }
        if (!Objects.equals(this.vendaId, other.vendaId)) {
            return false;
        }
        if (!Objects.equals(this.quantidade, other.quantidade)) {
            return false;
        }
        if (!Objects.equals(this.precoTotal, other.precoTotal)) {
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
        return "Produto_Vendido{" + "id=" + id + ", produto_id=" + produtoId + ", venda_id=" + vendaId + ", quantidade=" + quantidade + ", preco_total=" + precoTotal + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", active=" + active + '}';
    }
}
