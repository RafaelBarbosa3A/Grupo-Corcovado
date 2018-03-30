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
@Table(name = "produto_reposicao")
public class Produto_Reposicao implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")private Long id;
    @Column(name = "reposicao_id")private Long reposicao_id;
    @Column(name = "produto_id")private Long produto_id;
    @Column(name = "quantidade")private Integer quantidade;
    @Column(name = "created_at")private GregorianCalendar created_at;
    @Column(name = "updated_at")private GregorianCalendar updated_at;
    @Column(name = "active")private boolean active;

    public Produto_Reposicao() {
        this.id = 0L;
    }

    public Produto_Reposicao(Long id, Long reposicao_id, Long produto_id, Integer quantidade, GregorianCalendar created_at, GregorianCalendar updated_at, boolean active) {
        this.id = id;
        this.reposicao_id = reposicao_id;
        this.produto_id = produto_id;
        this.quantidade = quantidade;
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

    public Long getReposicao_id() {
        return reposicao_id;
    }

    public void setReposicao_id(Long reposicao_id) {
        this.reposicao_id = reposicao_id;
    }

    public Long getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(Long produto_id) {
        this.produto_id = produto_id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
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
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.reposicao_id);
        hash = 97 * hash + Objects.hashCode(this.produto_id);
        hash = 97 * hash + Objects.hashCode(this.quantidade);
        hash = 97 * hash + Objects.hashCode(this.created_at);
        hash = 97 * hash + Objects.hashCode(this.updated_at);
        hash = 97 * hash + (this.active ? 1 : 0);
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
        final Produto_Reposicao other = (Produto_Reposicao) obj;
        if (this.active != other.active) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.reposicao_id, other.reposicao_id)) {
            return false;
        }
        if (!Objects.equals(this.produto_id, other.produto_id)) {
            return false;
        }
        if (!Objects.equals(this.quantidade, other.quantidade)) {
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
        return "Produto_Reposicao{" + "id=" + id + ", reposicao_id=" + reposicao_id + ", produto_id=" + produto_id + ", quantidade=" + quantidade + ", created_at=" + created_at + ", updated_at=" + updated_at + ", active=" + active + '}';
    }
}
