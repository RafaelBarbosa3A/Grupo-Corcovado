package br.senac.corcovado.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author wesley
 */

@Entity
@Table(name = "departamento")
public class Departamento implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;
    @Column(name = "nome") private String nome;
    
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Categoria> categorias;

    @Column(name = "created_at") @Temporal(TemporalType.TIMESTAMP) private GregorianCalendar createdAt;
    @Column(name = "updated_at") @Temporal(TemporalType.TIMESTAMP) private GregorianCalendar updatedAt;
    @Column(name = "active") private boolean active;

    public Departamento() {
        this.id = 0L;
        this.categorias = new ArrayList<>();
        this.active = true;
    }
    
    public Departamento(Long id, String nome, List<Categoria> categorias, GregorianCalendar createdAt, GregorianCalendar updatedAt, boolean active) {
        this.id = id;
        this.nome = nome;
        this.categorias = categorias;
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

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public GregorianCalendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(GregorianCalendar createdAt) {
        this.createdAt = createdAt;
    }

    public GregorianCalendar getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(GregorianCalendar updatedAt) {
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
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.nome);
        hash = 79 * hash + Objects.hashCode(this.categorias);
        hash = 79 * hash + Objects.hashCode(this.createdAt);
        hash = 79 * hash + Objects.hashCode(this.updatedAt);
        hash = 79 * hash + (this.active ? 1 : 0);
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
        final Departamento other = (Departamento) obj;
        if (this.active != other.active) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.categorias, other.categorias)) {
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
        return "Departamento{" + "id=" + id + ", nome=" + nome + ", categorias=" + categorias + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", active=" + active + '}';
    }


}
