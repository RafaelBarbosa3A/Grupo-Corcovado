package br.senac.corcovado.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Set;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;
import javax.persistence.PostLoad;

/**
 *
 * @author wesley
 */
@Entity
@Table(name = "produto")
@SQLDelete(sql = "UPDATE produto SET active = false WHERE id = ?")
@Loader(namedQuery = "findProdutoById")
@NamedQuery(name = "findProdutoById", query = "SELECT p FROM Produto p WHERE p.id = ?1")
@Where(clause = "active = true")
//Gambiarraaaaaaaaa
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "precos"})
public class Produto implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") private Long id;
    
    @NotEmpty(message = "Favor digitar um nome")
    @Size(min=1,max=255,message="Favor digitar um nome entre 1 á 255 letras")
    @Column(name = "nome") private String nome;
    @NotEmpty(message = "Favor digitar uma descrição")
    @Size(min=1,max=255,message="Favor digitar uma descrição entre 1 á 255 letras")
    @Column(name = "descricao") private String descricao;
    @NotEmpty(message = "Favor digitar um nome de fabricante")
    @Size(min=1,max=255,message="Favor digitar um nome de fabricante entre 1 á 255 letras")
    @Column(name = "fabricante") private String fabricante;
    @NotEmpty(message = "Favor digitar um código")
    @Size(min=1,max=255,message="Favor digitar um código entre 1 á 255 letras")
    @Column(name = "codigo") private String codigo;
    @Column(name = "imagem") private String imagem;
    @Column(name = "estoque") private int estoque;
    @Column(name = "reservado") private int reservado;
    
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "categoria_id", referencedColumnName = "id") private Categoria categoria;
    @OneToMany(mappedBy = "produto") private List<Preco> precos;
    
    // Mantem o preco selecionado (baseado no perfil do usuario.
    @Transient private Double preco;
    
    @Column(name = "created_at") private Long createdAt;
    @Column(name = "updated_at") private Long updatedAt;
    @Column(name = "active") private boolean active;
    

    public Produto() {
        this.id = 0L;
        //this.precos = new ArrayList<>();
        this.active = true;
    }

    public Produto(Long id, String nome, String descricao, String fabricante, String codigo, String imagem, int estoque, int reservado, Categoria categoria, Long createdAt, Long updatedAt, boolean active) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.fabricante = fabricante;
        this.codigo = codigo;
        this.imagem = imagem;
        this.estoque = estoque;
        this.reservado = reservado;
        this.categoria = categoria;
        //this.precos = new ArrayList<>();
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

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFabricante() {
        return fabricante;
    }
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getImagem() {
        return imagem;
    }
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getEstoque() {
        return estoque;
    }
    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public int getReservado() {
        return reservado;
    }
    public void setReservado(int reservado) {
        this.reservado = reservado;
    }

    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public List<Preco> getPrecos() {
        return precos;
    }
    public void setPrecos(List<Preco> precos) {
        this.precos = precos;
    }
    
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
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

    @Override public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.nome);
        hash = 59 * hash + Objects.hashCode(this.descricao);
        hash = 59 * hash + Objects.hashCode(this.fabricante);
        hash = 59 * hash + Objects.hashCode(this.codigo);
        hash = 59 * hash + Objects.hashCode(this.imagem);
        hash = 59 * hash + Objects.hashCode(this.estoque);
        hash = 59 * hash + Objects.hashCode(this.reservado);
        hash = 59 * hash + Objects.hashCode(this.categoria);
       // hash = 59 * hash + Objects.hashCode(this.precos);
        hash = 59 * hash + Objects.hashCode(this.createdAt);
        hash = 59 * hash + Objects.hashCode(this.updatedAt);
        hash = 59 * hash + (this.active ? 1 : 0);
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
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override public String toString() {
        return "Produto{" + "id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", fabricante=" + fabricante + ", codigo=" + codigo + ", imagem=" + imagem + ", estoque=" + estoque + ", reservado=" + reservado + ", categoria=" + categoria + /*", precos=" + precos +*/ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", active=" + active + '}';
    }

    // === JPA Gambiarras ===

    @PrePersist
    private void prePersist() {
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = System.currentTimeMillis();
    }

    @PostLoad
    private void loadPreco() {
        this.preco = this.precos.stream()
                .sorted().findFirst()
                .orElse(new Preco(0L, 0D, this, Nivel.BASIC, System.currentTimeMillis(), System.currentTimeMillis()))
                .getPreco();
    }
    
    @PreRemove
    private void preRemove() {
        this.active = false;
    }
}
