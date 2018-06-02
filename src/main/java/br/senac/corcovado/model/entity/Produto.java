package br.senac.corcovado.model.entity;

import java.io.Serializable;
import java.util.List;
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
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;

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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id") private Long id;
    
    @NotEmpty(message = "Favor digitar um nome")
    @Size(min=1, max=255, message="Favor digitar um nome entre 1 á 255 letras")
    @Column(name = "nome") private String nome;
    
    @NotEmpty(message = "Favor digitar uma descrição")
    @Size(min=1, max=4096, message="Favor digitar uma descrição entre 1 á 255 letras")
    @Column(name = "descricao", length = 4096) private String descricao;
    
    @NotEmpty(message = "Favor digitar um nome de fabricante")
    @Size(min=1, max=255, message="Favor digitar um nome de fabricante entre 1 á 255 letras")
    @Column(name = "fabricante") private String fabricante;
    
    @NotEmpty(message = "Favor digitar um código")
    @Size(min=1, max=255, message="Favor digitar um código entre 1 á 255 letras")
    @Column(name = "codigo") private String codigo;
    
    @Column(name = "imagem") private String imagem;
    
    @Min(value = 0, message = "Estoque não pode ser negativo")
    @Column(name = "estoque") private int estoque;
    
    @Min(value = 0, message = "Reservado não pode ser negativo")
    @Column(name = "reservado") private int reservado;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

    // NOPE! NOOO!!! STUPID FUCKING IDEA!!!!!
    // replaced
    // @OneToMany(mappedBy = "produto") private List<Preco> precos;
    // @Transient private Double preco;

    @Min(value = 0, message = "Favor digitar um preço não negativo")
    @Column(name = "preco") private Double preco;
    
    @OneToMany(mappedBy = "produto") private Set<Desconto> descontos;

    @Column(name = "created_at") private Long createdAt;
    @Column(name = "updated_at") private Long updatedAt;
    @Column(name = "active") private boolean active;
    
    public Produto() {
        this.id = 0L;
        this.active = true;
    }

    public Produto(Long id, String nome, String descricao, String fabricante, String codigo, String imagem, int estoque, int reservado, Set<Desconto> descontos, Categoria categoria, Double preco, Long createdAt, Long updatedAt, boolean active) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.fabricante = fabricante;
        this.codigo = codigo;
        this.imagem = imagem;
        this.estoque = estoque;
        this.reservado = reservado;
        this.descontos = descontos;
        this.categoria = categoria;
        this.preco = preco;
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

    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Set<Desconto> getDescontos() {
        return descontos;
    }
    public void setDescontos(Set<Desconto> descontos) {
        this.descontos = descontos;
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
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.nome);
        hash = 29 * hash + Objects.hashCode(this.descricao);
        hash = 29 * hash + Objects.hashCode(this.fabricante);
        hash = 29 * hash + Objects.hashCode(this.codigo);
        hash = 29 * hash + Objects.hashCode(this.imagem);
        hash = 29 * hash + this.estoque;
        hash = 29 * hash + this.reservado;
        hash = 29 * hash + Objects.hashCode(this.preco);
        hash = 29 * hash + Objects.hashCode(this.descontos);
        hash = 29 * hash + Objects.hashCode(this.createdAt);
        hash = 29 * hash + Objects.hashCode(this.updatedAt);
        hash = 29 * hash + (this.active ? 1 : 0);
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
        return "Produto{" + "id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", fabricante=" + fabricante + ", codigo=" + codigo + ", imagem=" + imagem + ", estoque=" + estoque + ", reservado=" + reservado + ", categoria=" + categoria + ", preco=" + preco + ", descontos=" + descontos + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", active=" + active + '}';
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
