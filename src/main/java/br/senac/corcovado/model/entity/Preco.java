package br.senac.corcovado.model.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author wesley
 */

@Entity
@Table(name = "preco", uniqueConstraints={ @UniqueConstraint(columnNames = {"produto_id", "nivel"})} )
/*
@SQLDelete(sql = "UPDATE preco SET active = false WHERE id = ?")
@Loader(namedQuery = "findPrecoById")
@NamedQuery(name = "findPrecoById", query = "SELECT p FROM Preco p WHERE p.id = ?1")
@Where(clause = "active = true")
*/
//Gambiarraaaaaaaaa
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "produto"})
public class Preco implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;
    @Column(name = "preco") private Double preco;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "produto_id", referencedColumnName = "id") private Produto produto;
    @Enumerated(EnumType.STRING) @Column(name = "nivel") private Nivel nivel;
    @Column(name = "created_at") private Long createdAt;
    @Column(name = "updated_at") private Long updatedAt;
    //@Column(name = "active") private boolean active;

    public Preco() {
        this.id = 0L;
        //this.active = true;
    }

    public Preco(Long id, Double preco, Produto produto, Nivel nivel, Long createdAt, Long updatedAt /*, boolean active*/) {
        this.id = id;
        this.preco = preco;
        this.produto = produto;
        this.nivel = nivel;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        //this.active = active;
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

    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Nivel getNivel() {
        return nivel;
    }
    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
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

    /*
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    */

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.preco);
        hash = 89 * hash + Objects.hashCode(this.produto);
        hash = 89 * hash + Objects.hashCode(this.nivel);
        hash = 89 * hash + Objects.hashCode(this.createdAt);
        hash = 89 * hash + Objects.hashCode(this.updatedAt);
        //hash = 89 * hash + (this.active ? 1 : 0);
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
        final Preco other = (Preco) obj;
        /*
        if (this.active != other.active) {
            return false;
        }
        */
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.preco, other.preco)) {
            return false;
        }
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        if (this.nivel != other.nivel) {
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
        return "Preco{" + "id=" + id + ", preco=" + preco + ", produto=" + produto + ", nivel=" + nivel + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt /*+ ", active=" + active*/ + '}';
    }
    
    // === JPA Gambiarras ===
    
    @PrePersist
    private void prePersist() {
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }
    
    @PreUpdate
    private void preUpdate() {
        this.updatedAt = System.currentTimeMillis();
    }

    /*
    @PreRemove
    private void preRemove() {
        this.active = false;
    }
    */
}
