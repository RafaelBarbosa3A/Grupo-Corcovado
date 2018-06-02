package br.senac.corcovado.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
/**
 *
 * @author wesley
 */

@Entity
@Table(name = "endereco")
@SQLDelete(sql = "UPDATE endereco SET active = false WHERE id = ?")
@Loader(namedQuery = "findEnderecoById")
@NamedQuery(name = "findEnderecoById", query = "SELECT e FROM Endereco e WHERE e.id = ?1")
@Where(clause = "active = true")
//Gambiarraaaaaaaaa
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "pessoa"})
public class Endereco implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "id") private Long id;
    @NotEmpty(message = "Favor digitar um endereço")
    
    @Size(min=1,max=255,message="Favor digitar um endereço entre 1 á 255 letras")
    @Column(name = "rua") private String rua;
    
    @NotEmpty(message = "Favor digitar um número")
    @Column(name = "numero") private String numero;
    
    @NotEmpty(message = "Favor digitar um bairro")
    @Size(min=1,max=255,message="Favor digitar um bairro entre 1 á 255 letras")
    @Column(name = "bairro") private String bairro;
    
    @NotEmpty(message = "Favor digitar uma cidade")
    @Size(min=1,max=255,message="Favor digitar uma cidade entre 1 á 255 letras")
    @Column(name = "cidade") private String cidade;
    
    @NotEmpty(message = "Favor digitar um estado")
    @Size(min=1,max=255,message="Favor digitar um estado entre 1 á 255 letras")
    @Column(name = "estado") private String estado;
    
    @NotEmpty(message = "Favor digitar um cep")
    @Size(min=1,max=255,message="Favor digitar um cep entre 1 á 8 letras")
    @Column(name = "cep") private String cep;
    
    @Column(name = "complemento") private String complemento;
    
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id") private Pessoa pessoa;
    
    @Column(name = "created_at") private Long createdAt;
    @Column(name = "updated_at") private Long updatedAt;
    @Column(name = "active") private boolean active;
    
    public Endereco() {
        this.id = 0L;
        this.active = true;
    }

    public Endereco(Long id, String rua, String numero, String bairro, String cidade, String estado, String cep, String complemento, Pessoa pessoa, Long createdAt, Long updatedAt, boolean active) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.complemento = complemento;
        this.pessoa = pessoa;
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

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
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

    public Pessoa getPessoa() {
        return pessoa;
    }
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.rua);
        hash = 83 * hash + Objects.hashCode(this.numero);
        hash = 83 * hash + Objects.hashCode(this.bairro);
        hash = 83 * hash + Objects.hashCode(this.cidade);
        hash = 83 * hash + Objects.hashCode(this.estado);
        hash = 83 * hash + Objects.hashCode(this.cep);
        hash = 83 * hash + Objects.hashCode(this.complemento);
        hash = 83 * hash + Objects.hashCode(this.createdAt);
        hash = 83 * hash + Objects.hashCode(this.updatedAt);
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
        final Endereco other = (Endereco) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Endereco{" + "id=" + id + ", rua=" + rua + ", numero=" + numero + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", cep=" + cep + ", complemento=" + complemento + ", pessoa=" + pessoa + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", active=" + active + '}';
    }
    
    // === JPA Porco ===

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
