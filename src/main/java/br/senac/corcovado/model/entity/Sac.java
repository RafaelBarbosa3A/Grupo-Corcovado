package br.senac.corcovado.model.entity;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 *
 * @author wesley
 */

@Entity
@Table(name = "sac")
public class Sac implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;
        
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id") private Pessoa pessoa;
    
    @Size(max = 1024)
    @NotEmpty(message = "Favor digitar uma mensagem")
    @Column(name = "mensagem", length=1024) private String mensagem;
    
    @OneToMany(mappedBy = "sac") private Set<Resposta> respostas;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status_mensagem") private StatusMensagem statusMensagem;
    
    @Column(name = "created_at") private long createdAt;
    @Column(name = "updated_at") private long updatedAt;
    @Column(name = "active") private boolean active;

    public Sac() {
        this.id = 0L;
        this.respostas = new HashSet<>();
        this.statusMensagem = StatusMensagem.NOVO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Resposta> getRespostas() {
        return respostas;
    }
    public void setRespostas(Set<Resposta> respostas) {
        this.respostas = respostas;
    }
    
    public StatusMensagem getStatusMensagem() {
        return statusMensagem;
    }
    public void setStatusMensagem(StatusMensagem statusMensagem) {
        this.statusMensagem = statusMensagem;
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
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.mensagem);
        hash = 17 * hash + Objects.hashCode(this.statusMensagem);
        hash = 17 * hash + (int) (this.createdAt ^ (this.createdAt >>> 32));
        hash = 17 * hash + (int) (this.updatedAt ^ (this.updatedAt >>> 32));
        hash = 17 * hash + (this.active ? 1 : 0);
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
        final Sac other = (Sac) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sac{id=" + id + ", mensagem=" + mensagem + ", statusMensagem=" + statusMensagem + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", active=" + active + '}';
    }
}
