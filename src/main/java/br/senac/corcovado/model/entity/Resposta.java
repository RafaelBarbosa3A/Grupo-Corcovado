package br.senac.corcovado.model.entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author wesley
 */

@Entity
@Table(name = "resposta")
public class Resposta implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private long id;
    
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "sac_id", referencedColumnName = "id") private Sac sac;
    
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id") private Pessoa pessoa;
    
    @Size(max = 1024)
    @NotEmpty(message = "Favor digitar uma mensagem")
    @Column(name = "mensagem", length=1024) private String mensagem;
    
    @Column(name = "created_at") private long createdAt;
    @Column(name = "updated_at") private long updatedAt;
    @Column(name = "active") private boolean active;

    public Resposta() {
        this.id = 0L;
        this.active = true;
    }

    public Resposta(Long id, Sac sac, Pessoa pessoa, String mensagem, long createdAt, long updatedAt, boolean active) {
        this.id = id;
        this.sac = sac;
        this.pessoa = pessoa;
        this.mensagem = mensagem;
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

    public Sac getSac() {
        return sac;
    }
    public void setSac(Sac sac) {
        this.sac = sac;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
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

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.mensagem);
        hash = 83 * hash + (int) (this.createdAt ^ (this.createdAt >>> 32));
        hash = 83 * hash + (int) (this.updatedAt ^ (this.updatedAt >>> 32));
        hash = 83 * hash + (this.active ? 1 : 0);
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
        final Resposta other = (Resposta) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Resposta{" + "id=" + id + ", mensagem=" + mensagem + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", active=" + active + '}';
    }
}
