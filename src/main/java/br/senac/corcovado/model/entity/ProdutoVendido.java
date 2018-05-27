package br.senac.corcovado.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 *
 * @author wesley
 */
@Entity
@Table(name = "produto_vendido")
@SQLDelete(sql = "UPDATE produto_vendido SET active = false WHERE id = ?")
@Loader(namedQuery = "findProdutoVendidoById")
@NamedQuery(name = "findProdutoVendidoById", query = "SELECT pv FROM ProdutoVendido pv WHERE pv.id = ?1")
@Where(clause = "active = true")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler",  "venda"})
public class ProdutoVendido implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") private Long id;
    
    @ManyToOne(targetEntity = Produto.class, optional = false)
    @JoinColumn(name = "produto_id", referencedColumnName = "id", nullable = false)
    private Produto produto;
    
    @ManyToOne(targetEntity = Venda.class, optional = false)
    @JoinColumn(name = "venda_id", referencedColumnName = "id", nullable = false)
    private Venda venda;
    
    @Column(name = "quantidade") private Integer quantidade;
    @Column(name = "preco_total") private Double precoTotal;
    
    @Column(name = "created_at") private Long createdAt;
    @Column(name = "updated_at") private Long updatedAt;
    @Column(name = "active") private boolean active;    

    public ProdutoVendido() {
        this.id = 0L;
        this.quantidade = 0;
        this.active = true;
    }

    public ProdutoVendido(Long id, Produto produto, Venda venda, Integer quantidade, Double precoTotal, Long createdAt, Long updatedAt, boolean active) {
        this.id = id;
        this.produto = produto;
        this.venda = venda;
        this.quantidade = quantidade;
        this.precoTotal = precoTotal;
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }
    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }
    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
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
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.produto);
        hash = 73 * hash + Objects.hashCode(this.venda);
        hash = 73 * hash + Objects.hashCode(this.quantidade);
        hash = 73 * hash + Objects.hashCode(this.precoTotal);
        hash = 73 * hash + Objects.hashCode(this.createdAt);
        hash = 73 * hash + Objects.hashCode(this.updatedAt);
        hash = 73 * hash + (this.active ? 1 : 0);
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
        final ProdutoVendido other = (ProdutoVendido) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProdutoVendido{" + "id=" + id + ", produto=" + produto + ", venda=" + venda + ", quantidade=" + quantidade + ", precoTotal=" + precoTotal + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", active=" + active + '}';
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
}
