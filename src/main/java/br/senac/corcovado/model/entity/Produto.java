package br.senac.corcovado.model.entity;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author wesley
 */
@Entity
@Table(name = "produto")
public class Produto implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;
    @Column(name = "nome") private String nome;
    @Column(name = "descricao") private String descricao;
    @Column(name = "fabricante") private String fabricante;
    @Column(name = "codigo") private String codigo;
    @Column(name = "estoque") private Integer estoque;
    @Column(name = "reservado") private Integer reservado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", foreignKey = @ForeignKey(name = "id")) 
    private Categoria categoria;
    
    @OneToMany(mappedBy = "preco", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Preco> precos;
    
    @Column(name = "created_at") @Temporal(TemporalType.TIMESTAMP) private GregorianCalendar createdAt;
    @Column(name = "updated_at") @Temporal(TemporalType.TIMESTAMP) private GregorianCalendar updatedAt;
    @Column(name = "active") private boolean active;

    public Produto() {
        this.id = 0L;
        this.active = true;
    }

    public Produto(Long id, String nome, String descricao, String fabricante, String codigo, Integer estoque, Integer reservado, Categoria categoria, GregorianCalendar createdAt, GregorianCalendar updatedAt, boolean active) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.fabricante = fabricante;
        this.codigo = codigo;
        this.estoque = estoque;
        this.reservado = reservado;
        this.categoria = categoria;
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

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Integer getReservado() {
        return reservado;
    }

    public void setReservado(Integer reservado) {
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

    public GregorianCalendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(GregorianCalendar createdAt) {
        this.createdAt = createdAt;
    }

    public GregorianCalendar getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(GregorianCalendar updatedAt) {
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
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.nome);
        hash = 17 * hash + Objects.hashCode(this.descricao);
        hash = 17 * hash + Objects.hashCode(this.fabricante);
        hash = 17 * hash + Objects.hashCode(this.codigo);
        hash = 17 * hash + Objects.hashCode(this.estoque);
        hash = 17 * hash + Objects.hashCode(this.reservado);
        hash = 17 * hash + Objects.hashCode(this.categoria);
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
        final Produto other = (Produto) obj;
        if (this.active != other.active) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.fabricante, other.fabricante)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.estoque, other.estoque)) {
            return false;
        }
        if (!Objects.equals(this.reservado, other.reservado)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
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
        return "Produto{" + "id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", fabricante=" + fabricante + ", codigo=" + codigo + ", estoque=" + estoque + ", reservado=" + reservado + ", categoria=" + categoria + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", active=" + active + '}';
    }
}
