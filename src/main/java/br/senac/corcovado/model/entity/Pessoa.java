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
@Table(name = "pessoa")
public class Pessoa implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")private Long id;
    @Column(name = "nome")private String nome;
    @Column(name = "documento")private String documento;
    @Column(name = "login")private String login;
    @Column(name = "senha")private String senha;
    @Column(name = "nivel_id")private Long nivel_id;
    @Column(name = "cargo_id")private Long cargo_id;
    @Column(name = "created_at")private GregorianCalendar created_at;
    @Column(name = "updated_at")private GregorianCalendar updated_at;
    @Column(name = "active")private boolean active;

    public Pessoa() {
        this.id = 0L;
    }

    public Pessoa(Long id, String nome, String documento, String login, String senha, Long nivel_id, Long cargo_id, GregorianCalendar created_at, GregorianCalendar updated_at, boolean active) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.login = login;
        this.senha = senha;
        this.nivel_id = nivel_id;
        this.cargo_id = cargo_id;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getNivel_id() {
        return nivel_id;
    }

    public void setNivel_id(Long nivel_id) {
        this.nivel_id = nivel_id;
    }

    public Long getCargo_id() {
        return cargo_id;
    }

    public void setCargo_id(Long cargo_id) {
        this.cargo_id = cargo_id;
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
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.nome);
        hash = 97 * hash + Objects.hashCode(this.documento);
        hash = 97 * hash + Objects.hashCode(this.login);
        hash = 97 * hash + Objects.hashCode(this.senha);
        hash = 97 * hash + Objects.hashCode(this.nivel_id);
        hash = 97 * hash + Objects.hashCode(this.cargo_id);
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
        final Pessoa other = (Pessoa) obj;
        if (this.active != other.active) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.documento, other.documento)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nivel_id, other.nivel_id)) {
            return false;
        }
        if (!Objects.equals(this.cargo_id, other.cargo_id)) {
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
        return "Pessoa{" + "id=" + id + ", nome=" + nome + ", documento=" + documento + ", login=" + login + ", senha=" + senha + ", nivel_id=" + nivel_id + ", cargo_id=" + cargo_id + ", created_at=" + created_at + ", updated_at=" + updated_at + ", active=" + active + '}';
    }
}
