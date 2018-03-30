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
@Table(name = "sac")
public class SAC implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")private Long id;
    @Column(name = "cliente_id")private Long cliente_id;
    @Column(name = "contato")private String contato;
    @Column(name = "mensagem")private String mensagem;
    @Column(name = "status_mensagem")private String status_mensagem;
    @Column(name = "created_at")private GregorianCalendar created_at;
    @Column(name = "updated_at")private GregorianCalendar updated_at;
    @Column(name = "active")private boolean active;

    public SAC() {
        this.id = 0L;
    }

    public SAC(Long id, Long cliente_id, String contato, String mensagem, String status_mensagem, GregorianCalendar created_at, GregorianCalendar updated_at, boolean active) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.contato = contato;
        this.mensagem = mensagem;
        this.status_mensagem = status_mensagem;
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

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getStatus_mensagem() {
        return status_mensagem;
    }

    public void setStatus_mensagem(String status_mensagem) {
        this.status_mensagem = status_mensagem;
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
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.cliente_id);
        hash = 89 * hash + Objects.hashCode(this.contato);
        hash = 89 * hash + Objects.hashCode(this.mensagem);
        hash = 89 * hash + Objects.hashCode(this.status_mensagem);
        hash = 89 * hash + Objects.hashCode(this.created_at);
        hash = 89 * hash + Objects.hashCode(this.updated_at);
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
        final SAC other = (SAC) obj;
        if (this.active != other.active) {
            return false;
        }
        if (!Objects.equals(this.contato, other.contato)) {
            return false;
        }
        if (!Objects.equals(this.mensagem, other.mensagem)) {
            return false;
        }
        if (!Objects.equals(this.status_mensagem, other.status_mensagem)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
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
        return "SAC{" + "id=" + id + ", cliente_id=" + cliente_id + ", contato=" + contato + ", mensagem=" + mensagem + ", status_mensagem=" + status_mensagem + ", created_at=" + created_at + ", updated_at=" + updated_at + ", active=" + active + '}';
    }
}
