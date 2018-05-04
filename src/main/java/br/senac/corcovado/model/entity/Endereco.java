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
@Table(name = "endereco")
public class Endereco implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;
    @NotEmpty(message = "Favor digitar um endereço")
    @Size(min=1,max=255,message="Favor digitar um endereço entre 1 á 255 letras")
    @Column(name = "rua") private String rua;
    @NotEmpty(message = "Favor digitar um número")
    @Size(min=1,max=255,message="Favor digitar um número entre 1 á 10000 letras")
    @Column(name = "numero") private int numero;
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
    @NotEmpty(message = "Favor digitar uma cidade")
    @Column(name = "principal") private boolean principal;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "pessoa_id", referencedColumnName = "id") private Pessoa pessoa;
    
    @Column(name = "created_at") private Long createdAt;
    @Column(name = "updated_at") private Long updatedAt;
    
    public Endereco() {
        this.id = 0L;
    }

    public Endereco(Long id, String rua, int numero, String bairro, String cidade, String estado, String cep, String complemento, boolean principal, Pessoa pessoa, Long createdAt, Long updatedAt) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.complemento = complemento;
        this.principal = principal;
        this.pessoa = pessoa;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Pessoa getPessoa() {
        return pessoa;
    }
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public boolean isPrincipal() {
        return principal;
    }
    public void setPrincipal(boolean principal) {
        this.principal = principal;
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

    @Override public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.rua);
        hash = 79 * hash + this.numero;
        hash = 79 * hash + Objects.hashCode(this.bairro);
        hash = 79 * hash + Objects.hashCode(this.cidade);
        hash = 79 * hash + Objects.hashCode(this.estado);
        hash = 79 * hash + Objects.hashCode(this.cep);
        hash = 79 * hash + Objects.hashCode(this.complemento);
        hash = 79 * hash + (this.principal ? 1 : 0);
        hash = 79 * hash + Objects.hashCode(this.pessoa);
        hash = 79 * hash + Objects.hashCode(this.createdAt);
        hash = 79 * hash + Objects.hashCode(this.updatedAt);
        return hash;
    }

    @Override public boolean equals(Object obj) {
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
        if (this.principal != other.principal) {
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.pessoa, other.pessoa)) {
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

    @Override public String toString() {
        return "Endereco{" + "id=" + id + ", rua=" + rua + ", numero=" + numero + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", cep=" + cep + ", complemento=" + complemento + ", principal=" + principal + ", pessoa=" + pessoa + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
}
