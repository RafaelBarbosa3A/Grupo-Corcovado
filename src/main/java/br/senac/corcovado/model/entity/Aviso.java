package br.senac.corcovado.model.entity;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "produto_id", referencedColumnName = "id") private Produto produto;
    
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id") private Pessoa pessoa;
    
    @Column(name = "created_at") private long createdAt;
    @Column(name = "updated_at") private long updatedAt;

    public Aviso() {
        this.id = 0L;
    }

    public Aviso(Produto produto, Pessoa pessoa) {
        this.id = 0L;
        this.produto = produto;
        this.pessoa = pessoa;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + (int) (this.createdAt ^ (this.createdAt >>> 32));
        hash = 47 * hash + (int) (this.updatedAt ^ (this.updatedAt >>> 32));
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Aviso{" + "id=" + id + ", produto=" + produto + ", pessoa=" + pessoa + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
}
