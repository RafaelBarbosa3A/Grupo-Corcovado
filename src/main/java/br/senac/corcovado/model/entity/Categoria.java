
package br.senac.corcovado.model.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author wesley
 */
@Entity
@Table(name = "categoria")
@SQLDelete(sql = "UPDATE categoria SET active = false WHERE id = ?")
@Loader(namedQuery = "findCategoriaById")
@NamedQuery(name = "findCategoriaById", query = "SELECT c FROM Categoria c WHERE c.id = ?1")
@Where(clause = "active = true")

//Gambiarraaaaaaaaa
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @NotEmpty(message = "Favor digitar um nome")
    @Size(min=1,max=255,message="Favor digitar um nome entre 1 á 255 letras")
    @Column(name = "nome")
    private String nome;

    //@ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "departamento_id", referencedColumnName = "id") private Departamento departamento;

    @Column(name = "created_at")
    private Long createdAt;
    @Column(name = "updated_at")
    private Long updatedAt;
    @Column(name = "active")
    private boolean active;

    public Categoria() {
        this.id = 0L;
        this.createdAt = 0L;
        this.active = true;
    }

    public Categoria(Long id, String nome, Long createdAt, Long updatedAt, boolean active) {
        this.id = id;
        this.nome = nome;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.nome);
        hash = 97 * hash + Objects.hashCode(this.createdAt);
        hash = 97 * hash + Objects.hashCode(this.updatedAt);
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
        final Categoria other = (Categoria) obj;
        if (this.active != other.active) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Categoria{" + "id=" + id + ", nome=" + nome + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
                + ", active=" + active + '}';
    }

    // === JPA Gambiarras ===

    @PrePersist 
    private void beforeCreate() {
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }

    @PreUpdate
    private void beforeUpdate() {
        this.updatedAt = System.currentTimeMillis();
    }
    
    @PreRemove
    private void softDelete() {
        this.active = false;
    }
}
