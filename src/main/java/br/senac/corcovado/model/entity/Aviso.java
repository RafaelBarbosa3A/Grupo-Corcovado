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
@Table(name = "aviso")
public class Aviso implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;
    @Column(name = "produto_id") private Long produtoId;
    @Column(name = "cliente_id") private Long clienteId;
    @Column(name = "created_at") private GregorianCalendar createdAt;
    @Column(name = "updated_at") private GregorianCalendar updatedAt;
    @Column(name = "active") private boolean active;    

    public Aviso() {
        this.id = 0L;
    }

    public Aviso(Long id, Long produto_id, Long cliente_id, GregorianCalendar createdAt, GregorianCalendar updatedAt, boolean active) {
        this.id = id;
        this.produtoId = produto_id;
        this.clienteId = cliente_id;
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

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
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
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.produtoId);
        hash = 89 * hash + Objects.hashCode(this.clienteId);
        hash = 89 * hash + Objects.hashCode(this.createdAt);
        hash = 89 * hash + Objects.hashCode(this.updatedAt);
        hash = 89 * hash + (this.active ? 1 : 0);
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
        final Aviso other = (Aviso) obj;
        if (this.active != other.active) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.produtoId, other.produtoId)) {
            return false;
        }
        if (!Objects.equals(this.clienteId, other.clienteId)) {
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
        return "Aviso{" + "id=" + id + ", produto_id=" + produtoId + ", cliente_id=" + clienteId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", active=" + active + '}';
    }
}
