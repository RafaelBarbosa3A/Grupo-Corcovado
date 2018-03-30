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
@Table(name = "resposta")
public class Resposta implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")private Long id;
    @Column(name = "sac_id")private Long sac_id;
    @Column(name = "mensagem")private String mensagem;
    @Column(name = "cliente_id")private Long cliente_id;
    @Column(name = "created_at")private GregorianCalendar created_at;
    @Column(name = "updated_at")private GregorianCalendar updated_at;
    @Column(name = "active")private boolean active;

    public Resposta() {
        this.id = 0L;
    }

    public Resposta(Long id, Long sac_id, String mensagem, Long cliente_id, GregorianCalendar created_at, GregorianCalendar updated_at, boolean active) {
        this.id = id;
        this.sac_id = sac_id;
        this.mensagem = mensagem;
        this.cliente_id = cliente_id;
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

    public Long getSac_id() {
        return sac_id;
    }

    public void setSac_id(Long sac_id) {
        this.sac_id = sac_id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
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
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.sac_id);
        hash = 29 * hash + Objects.hashCode(this.mensagem);
        hash = 29 * hash + Objects.hashCode(this.cliente_id);
        hash = 29 * hash + Objects.hashCode(this.created_at);
        hash = 29 * hash + Objects.hashCode(this.updated_at);
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
        final Resposta other = (Resposta) obj;
        if (this.active != other.active) {
            return false;
        }
        if (!Objects.equals(this.mensagem, other.mensagem)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.sac_id, other.sac_id)) {
            return false;
        }
        if (!Objects.equals(this.cliente_id, other.cliente_id)) {
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
        return "Resposta{" + "id=" + id + ", sac_id=" + sac_id + ", mensagem=" + mensagem + ", cliente_id=" + cliente_id + ", created_at=" + created_at + ", updated_at=" + updated_at + ", active=" + active + '}';
    }
}
