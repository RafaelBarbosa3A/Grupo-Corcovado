package br.senac.corcovado.model.entity;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author wesley
 */
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;
    @Column(name = "nome") private String nome;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id", foreignKey = @ForeignKey(name = "id")) 
    private Departamento departamento;
    
    @Column(name = "created_at") @Temporal(TemporalType.TIMESTAMP) private GregorianCalendar createdAt;
    @Column(name = "updated_at") @Temporal(TemporalType.TIMESTAMP) private GregorianCalendar updatedAt;
    @Column(name = "active") private boolean active;

    public Categoria() {
        this.id = 0L;
        this.active = true;
    }

    public Categoria(Long id, String nome, Departamento departamento, GregorianCalendar createdAt, GregorianCalendar updatedAt, boolean active) {
        this.id = id;
        this.nome = nome;
        this.departamento = departamento;
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

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
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
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.nome);
        hash = 97 * hash + Objects.hashCode(this.departamento);
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
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.departamento, other.departamento)) {
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
        return "Categoria{" + "id=" + id + ", nome=" + nome + ", departamento=" + departamento + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", active=" + active + '}';
    }
}
