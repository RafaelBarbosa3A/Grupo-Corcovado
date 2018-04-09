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
@Table(name = "endereco")
public class Endereco implements Serializable{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;
    @Column(name = "rua") private String rua;
    @Column(name = "numero") private int numero;
    @Column(name = "bairro") private String bairro;
    @Column(name = "cidade") private String cidade;
    @Column(name = "estado") private String estado;
    @Column(name = "cep") private String cep;
    @Column(name = "complemento") private String complemento;
    @Column(name = "principal") private String principal;
    @Column(name = "cliente_id") private Long clienteId;
    @Column(name = "created_at") private GregorianCalendar createdAt;
    @Column(name = "updated_at") private GregorianCalendar updatedAt;
    @Column(name = "active") private boolean active;
    
    public Endereco() {
        this.id = 0L;
    }

    public Endereco(Long id, String rua, int numero, String bairro, String cidade, String estado, String cep, String complemento, String principal, Long clienteId, GregorianCalendar createdAt, GregorianCalendar updatedAt, boolean active) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.complemento = complemento;
        this.principal = principal;
        this.clienteId = clienteId;
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

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public GregorianCalendar getCreated_at() {
        return createdAt;
    }

    public void setCreated_at(GregorianCalendar createdAt) {
        this.createdAt = createdAt;
    }

    public GregorianCalendar getUpdated_at() {
        return updatedAt;
    }

    public void setUpdated_at(GregorianCalendar updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public void setCreated_at(long timeInMillis) {
        this.createdAt = new GregorianCalendar();
        this.createdAt.setTimeInMillis(timeInMillis);
    }
    
    public void setUpdated_at(long timeInMillis) {
        this.updatedAt = new GregorianCalendar();
        this.updatedAt.setTimeInMillis(timeInMillis);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.rua);
        hash = 17 * hash + this.numero;
        hash = 17 * hash + Objects.hashCode(this.bairro);
        hash = 17 * hash + Objects.hashCode(this.cidade);
        hash = 17 * hash + Objects.hashCode(this.estado);
        hash = 17 * hash + Objects.hashCode(this.cep);
        hash = 17 * hash + Objects.hashCode(this.complemento);
        hash = 17 * hash + Objects.hashCode(this.principal);
        hash = 17 * hash + Objects.hashCode(this.clienteId);
        hash = 17 * hash + Objects.hashCode(this.createdAt);
        hash = 17 * hash + Objects.hashCode(this.updatedAt);
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
        final Endereco other = (Endereco) obj;
        if (this.numero != other.numero) {
            return false;
        }
        if (this.active != other.active) {
            return false;
        }
        if (!Objects.equals(this.rua, other.rua)) {
            return false;
        }
        if (!Objects.equals(this.bairro, other.bairro)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.cep, other.cep)) {
            return false;
        }
        if (!Objects.equals(this.complemento, other.complemento)) {
            return false;
        }
        if (!Objects.equals(this.principal, other.principal)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.clienteId, other.clienteId)) {
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
        return "Endereco{" + "id=" + id + ", rua=" + rua + ", numero=" + numero + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", cep=" + cep + ", complemento=" + complemento + ", principal=" + principal + ", clienteId=" + clienteId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", active=" + active + '}';
    }
}
