package br.senac.corcovado.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author wesley
 */

@Entity
@Table(name = "pessoa")
@SQLDelete(sql = "UPDATE pessoa SET active = false WHERE id = ?")
@Loader(namedQuery = "findPessoaById")
@NamedQuery(name = "findPessoaById", query = "SELECT p FROM Pessoa p WHERE p.id = ?1")
@Where(clause = "active = true")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "papeis"})
public class Pessoa implements UserDetails, Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;
    
    @NotEmpty(message = "Favor digitar um nome")
    @Size(min=1,max=255,message="Favor digitar um nome entre 1 á 255 letras")
    @Column(name = "nome") private String nome;
    
    @NotEmpty(message = "Favor digitar o número do documento")
    @Column(name = "documento") private String documento;
    
    @NotEmpty(message = "Favor digitar um e-mail")
    @Column(name = "email") private String email;
    
    @NotEmpty(message = "Favor digitar uma senha")
    //@Size(min=1,max=255,message="Favor digitar uma senha entre 1 á 15 letras")
    @Column(name = "senha") private String senha;
    
    @Enumerated(EnumType.STRING) 
    @Column(name = "nivel") private Nivel nivel;
    
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL) 
    private Set<Endereco> enderecos;
    
    @ManyToMany
    @JoinTable(name = "papel_pessoas", joinColumns = {@JoinColumn(name = "pessoa_id")}, inverseJoinColumns = {@JoinColumn(name = "papel_id")})
    private Set<Papel> papeis;
    
    @Column(name = "created_at") private Long createdAt;
    @Column(name = "updated_at") private Long updatedAt;
    @Column(name = "active") private boolean active;

    public Pessoa() {
        this.id = 0L;
        this.papeis = new HashSet<>();
        this.active = true;
    }

    public Pessoa(Long id, String nome, String documento, String email, String senha, Nivel nivel, Set<Endereco> enderecos, Set<Papel> papeis, Long createdAt, Long updatedAt, boolean active) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.senha = senha;
        this.nivel = nivel;
        this.enderecos = enderecos;
        this.papeis = papeis;
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

    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Nivel getNivel() {
        return nivel;
    }
    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Set<Papel> getPapeis() {
        return papeis;
    }
    public void setPapeis(Set<Papel> papeis) {
        this.papeis = papeis;
    }
    
    public Set<Endereco> getEnderecos() {
        return enderecos;
    }
    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
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
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.nome);
        hash = 19 * hash + Objects.hashCode(this.documento);
        hash = 19 * hash + Objects.hashCode(this.email);
        hash = 19 * hash + Objects.hashCode(this.senha);
        hash = 19 * hash + Objects.hashCode(this.nivel);
        hash = 19 * hash + Objects.hashCode(this.papeis);
        hash = 19 * hash + Objects.hashCode(this.createdAt);
        hash = 19 * hash + Objects.hashCode(this.updatedAt);
        hash = 19 * hash + (this.active ? 1 : 0);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", nome=" + nome + ", documento=" + documento + ", email=" + email + ", senha=" + senha + ", nivel=" + nivel + ", enderecos=" + enderecos + ", papeis=" + papeis + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", active=" + active + '}';
    }

    // === JPA Gambiarras ===

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

    // === Spring Security ===
    @Override public Set<Papel> getAuthorities() {
        return this.papeis;
    }

    @Override public String getPassword() {
        return this.senha;
    }

    @Override public String getUsername() {
        return this.email;
    }

    @Override public boolean isAccountNonExpired() {
        return this.active;
    }

    @Override public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override public boolean isCredentialsNonExpired() {
        return this.active;
    }

    @Override public boolean isEnabled() {
        return this.active;
    }
}
